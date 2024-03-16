package com.challenge.financial.scheduling.dto;

import com.challenge.financial.scheduling.entity.Transfer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {

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
    private Date dataTransfer;
    @NotBlank(message = "Campo requerido")
    private Date appointmentDate;

    public TransferDTO(Transfer entity) {
        this.targetAccount = entity.getTargetAccount();
        this.sourceAccount = entity.getSourceAccount();
        this.transferAmount = entity.getTransferAmount();
        this.rate = entity.getRate();
        this.dataTransfer = entity.getDataTransfer();
        this.appointmentDate = entity.getAppointmentDate();
    }

}
