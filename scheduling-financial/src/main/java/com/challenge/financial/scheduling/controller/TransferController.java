package com.challenge.financial.scheduling.controller;

import com.challenge.financial.scheduling.dto.TransferDTO;
import com.challenge.financial.scheduling.entity.Transfer;
import com.challenge.financial.scheduling.service.TransferService;
import com.challenge.financial.scheduling.service.exceptions.TransferValidationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping
    public ResponseEntity<List<TransferDTO>> findAllTransfer() {
        List<TransferDTO> list = transferService.findAllTransfer();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> createFinancial(@Valid @RequestBody Transfer transfer) {
        try {
            Transfer savedTransfer = transferService.scheduleTransfer(transfer);
            return ResponseEntity.ok(savedTransfer);
        } catch (TransferValidationException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}
