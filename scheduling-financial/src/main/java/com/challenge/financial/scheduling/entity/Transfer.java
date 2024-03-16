package com.challenge.financial.scheduling.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "source_account")
    private String sourceAccount;
    @Column(name = "target_account")
    private String targetAccount;
    @Column(name = "transfer_amount")
    private Double transferAmount;
    private Double rate;
    @Column(name = "data_transfer")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private Date dataTransfer;
    @Column(name = "appointment_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    private Date appointmentDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        if (sourceAccount == null || sourceAccount.trim().isEmpty()) {
            throw new RuntimeException("A conta de origem não pode ser nula ou vazia.");
        }
        this.sourceAccount = sourceAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        if (sourceAccount == null || sourceAccount.trim().isEmpty()) {
            throw new RuntimeException("A conta de destino não pode ser nula ou vazia");
        }
        this.targetAccount = targetAccount;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        if (transferAmount < 0) {
            throw new RuntimeException("O valor da transferência não pode ser negativo;");
        }
        this.transferAmount = transferAmount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        if (rate < 0) {
            throw new RuntimeException("A taxa da transferência não pode ser negativa.");
        }
        this.rate = rate;
    }

    public Date getDataTransfer() {
        return dataTransfer;
    }

    public void setDataTransfer(Date dataTransfer) {
        this.dataTransfer = dataTransfer;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", sourceAccount='" + sourceAccount + '\'' +
                ", targetAccount='" + targetAccount + '\'' +
                ", transferAmount=" + transferAmount +
                ", rate=" + rate +
                ", dataTransfer=" + dataTransfer +
                ", appointmentDate=" + appointmentDate +
                ", user=" + user +
                '}';
    }
}
