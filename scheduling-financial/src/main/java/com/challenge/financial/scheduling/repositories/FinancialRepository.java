package com.challenge.financial.scheduling.repositories;

import com.challenge.financial.scheduling.entities.Financial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialRepository extends JpaRepository<Financial, Long> {
}
