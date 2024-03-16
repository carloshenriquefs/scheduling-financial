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

import static com.challenge.financial.scheduling.constants.ConstantsMessage.*;
import static com.challenge.financial.scheduling.constants.ConstantsNumeric.*;

@Service
public class TransferService {
    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    @Autowired
    private TransferRepository repository;

    public List<TransferDTO> findAllTransfer() {
        List<Transfer> result = repository.findAll(Sort.by(TARGED_ACCOUNT));
        return result.stream().map(x -> new TransferDTO(x)).collect(Collectors.toList());
    }

    public Transfer scheduleTransfer(Transfer transfer) {
        validateTransfer(transfer);

        double rate = calculateRate(transfer.getDataTransfer(), transfer.getTransferAmount());
        double valueWithRate = transfer.getTransferAmount() - (transfer.getTransferAmount() * rate / 100);

        transfer.setRate(rate);

        DecimalFormat df = new DecimalFormat(NUMBER_ZERO, new DecimalFormatSymbols(Locale.US));
        transfer.setTransferAmount(Double.parseDouble(df.format(valueWithRate)));

        transfer.setDataTransfer(new Date());

        Transfer savedTransfer = repository.save(transfer);

        logger.info(TRANSFER_SCHEDULED_SUCCESSFULY, savedTransfer);

        return savedTransfer;
    }

    void validateTransfer(Transfer transfer) {
        if (transfer.getTransferAmount() <= 0) {
            throw new TransferValidationException(TRANSFERENCIA_MAIOR_QUE_ZERO, HttpStatus.BAD_REQUEST);
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate transferLocalDate = transfer.getDataTransfer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (transferLocalDate.isBefore(currentDate)) {
            throw new TransferValidationException(TRANSFERENCIA_NAO_PODE_SER_ANTES_DATA_ATUAL, HttpStatus.BAD_REQUEST);
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
            logger.info(CALCULATED_FEE, fee);
            return fee;
        } else {
            throw new TransferValidationException(NAO_EXISTEM_TAXAS_PARA_TRANSFERENCIA, HttpStatus.BAD_REQUEST);
        }
    }
}
