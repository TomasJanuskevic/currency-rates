package com.currencyrates.repository;

import com.currencyrates.dto.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    CurrencyRate findByCurrencyCode(String currencyCode);
}
