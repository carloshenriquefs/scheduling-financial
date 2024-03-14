package com.challenge.financial.scheduling.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_financial")
@AllArgsConstructor
@NoArgsConstructor
public class Financial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceAccount;
    private String targetAccount;
    private Double transferAmount;
    private Double rate;
    private LocalDate dataTransfer;
    private LocalDate appointmentDate;

}
