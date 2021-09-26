package com.currencyrates.repository;

import com.currencyrates.dto.CcyAmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CcyAmtRepository extends JpaRepository<CcyAmt, Long> {
    CcyAmt findByCcy(String currency);
}
