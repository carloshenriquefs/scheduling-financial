package com.challenge.financial.scheduling.service;

import com.challenge.financial.scheduling.dto.TransferDTO;
import com.challenge.financial.scheduling.entity.Transfer;
import com.challenge.financial.scheduling.repository.TransferRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferServiceTest {

    @Autowired
    private TransferService transferService;

    @MockBean
    private TransferRepository transferRepository;

    @Nested
    class getAllTransfer {
        @Test
        @DisplayName("Should get all transfer with sucess")
        void testGetAllTransfers() {
            List<Transfer> transfers = new ArrayList<>();

            when(transferRepository.findAll()).thenReturn(transfers);

            List<TransferDTO> result = transferService.findAllTransfer();

            assertEquals(transfers, result);
        }
    }

    @Nested
    class scheduleTransfer {
        @Test
        @DisplayName("Should schedule transfer with sucess")
        void testScheduleTransfer() {
            Transfer transfer = new Transfer();
            transfer.setTransferAmount(100.0);
            transfer.setDataTransfer(new Date());

            when(transferRepository.save(any())).thenReturn(transfer);

            Transfer result = transferService.scheduleTransfer(transfer);

            assertNotNull(result);
            assertEquals(97.5, result.getTransferAmount());
            assertNotNull(result.getDataTransfer());
            assertNotNull(result.getRate());
        }

        @Test
        @DisplayName("Should throw exception when transfer amount is equals the zero")
        void testInvalidTransferAmount() {
            Transfer transfer = new Transfer();
            transfer.setTransferAmount(0.0);

            assertThrows(RuntimeException.class, () -> transferService.validateTransfer(transfer));
        }

        @Test
        @DisplayName("Should throw exception when invalid transfer data")
        void testInvalidTransferData() {
            Transfer transfer = new Transfer();
            transfer.setTransferAmount(100.0);
            transfer.setDataTransfer(new Date(System.currentTimeMillis() - 86400000));

            assertThrows(RuntimeException.class, () -> transferService.validateTransfer(transfer));
        }
    }
}
