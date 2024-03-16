package com.challenge.financial.scheduling.service;

import com.challenge.financial.scheduling.dto.TransferDTO;
import com.challenge.financial.scheduling.entity.Transfer;
import com.challenge.financial.scheduling.repository.TransferRepository;
import com.challenge.financial.scheduling.service.exceptions.TransferValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.challenge.financial.scheduling.constants.Constants.DAYS_RANGES;
import static com.challenge.financial.scheduling.constants.Constants.TAX_RATES;

@Service
public class TransferService {
    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    @Autowired
    private TransferRepository repository;

    public List<TransferDTO> findAllTransfer() {
        List<Transfer> result = repository.findAll(Sort.by("targetAccount"));
        return result.stream().map(x -> new TransferDTO(x)).collect(Collectors.toList());
    }

    public Transfer scheduleTransfer(Transfer transfer) {
        validateTransfer(transfer);

        double rate = calculateRate(transfer.getDataTransfer(), transfer.getTransferAmount());
        double valueWithRate = transfer.getTransferAmount() - (transfer.getTransferAmount() * rate / 100);

        transfer.setRate(rate);

        DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        transfer.setTransferAmount(Double.parseDouble(df.format(valueWithRate)));

        transfer.setDataTransfer(new Date());

        Transfer savedTransfer = repository.save(transfer);

        logger.info("Transfer scheduled successfuly: {}", savedTransfer);

        return savedTransfer;
    }

    void validateTransfer(Transfer transfer) {
        if (transfer.getTransferAmount() <= 0) {
            throw new TransferValidationException("O valor da transferência precisa ser maior do que zero.", HttpStatus.BAD_REQUEST);
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate transferLocalDate = transfer.getDataTransfer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (transferLocalDate.isBefore(currentDate)) {
            throw new TransferValidationException("A data de transferência não pode ser antes da data atual.", HttpStatus.BAD_REQUEST);
        }
    }

    private double calculateRate(Date transferDate, double transferAmount) {
        LocalDate currentDate = LocalDate.now();
        LocalDate transferLocalDate = transferDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long days = Math.abs(ChronoUnit.DAYS.between(currentDate, transferLocalDate));

        int index = 0;
        while (index < DAYS_RANGES.length && days > DAYS_RANGES[index]) {
            index++;
        }

        if (index < TAX_RATES.length) {
            double fee = transferAmount > 0 ? TAX_RATES[index] : 0.0;
            logger.info("Calculated fee: {}", fee);
            return fee;
        } else {
            throw new TransferValidationException("No momento não existe taxas para transferência.", HttpStatus.BAD_REQUEST);
        }
    }
}
