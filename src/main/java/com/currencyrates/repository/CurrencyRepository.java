package com.currencyrates.repository;

import com.currencyrates.dto.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    @Modifying
    @Query(value = "TRUNCATE TABLE CURRENCY_LIST RESTART IDENTITY", nativeQuery = true)
    void truncateCurrencyList();
}
