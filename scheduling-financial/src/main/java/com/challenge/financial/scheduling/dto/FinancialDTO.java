package com.challenge.financial.scheduling.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialDTO {

    @NotBlank(message = "Campo requerido")
    @Size(max = 9, message = "O campo [targetAccount] precisa de 9 caracteres")
    private String targetAccount;
    @NotBlank(message = "Campo requerido")
    @Size(max = 9, message = "O campo [sourceAccount] precisa de 9 caracteres")
    private String sourceAccount;
    @NotBlank(message = "Campo requerido")
    @Positive(message = "O campo [transferAmount] deve ser positivo")
    private Double transferAmount;
    @NotBlank(message = "Campo requerido")
    @Positive(message = "O campo [rate] deve ser positivo")
    private Double rate;
    @NotBlank(message = "Campo requerido")
    private LocalDate dataTransfer;
    @NotBlank(message = "Campo requerido")
    private LocalDate appointmentDate;

}
