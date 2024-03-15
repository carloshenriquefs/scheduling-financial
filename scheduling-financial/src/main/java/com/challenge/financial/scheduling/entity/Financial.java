package com.challenge.financial.scheduling.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_financial")
public class Financial {

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
    private LocalDate dataTransfer;
    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
