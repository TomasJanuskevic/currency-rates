package com.currencyrates.repository;

import com.currencyrates.dto.currencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository extends JpaRepository<currencyRate, Long> {
    currencyRate findByCurrencyCode(String currencyCode);
}
