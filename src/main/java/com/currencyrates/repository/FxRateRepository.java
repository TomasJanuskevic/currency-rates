package com.currencyrates.repository;

import com.currencyrates.dto.FxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FxRateRepository extends JpaRepository<FxRate, Long> {
    @Modifying
    @Query(value = "TRUNCATE TABLE CURRENCY_RATE RESTART IDENTITY", nativeQuery = true)
    void truncateCurrencyRates();
}
