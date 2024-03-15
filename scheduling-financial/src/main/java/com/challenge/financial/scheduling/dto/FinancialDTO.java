package com.challenge.financial.scheduling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialDTO {

    private String sourceAccount;
    private String targetAccount;
    private Double transferAmount;
    private Double rate;
    private LocalDate dataTransfer;
    private LocalDate appointmentDate;

}
