package com.currencyrates.repository;

import com.currencyrates.dto.CcyNtry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CcyNtryRepository extends JpaRepository<CcyNtry, Long> {
}
