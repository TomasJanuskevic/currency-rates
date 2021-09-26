package com.currencyrates.service;

import com.currencyrates.dto.CcyNtry;
import com.currencyrates.repository.CcyNtryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CurrencyService {
    private final CcyNtryRepository ccyNtryRepository;

    public List<CcyNtry> getCurrencyList(){
        return ccyNtryRepository.findAll();
    }

    public List
}
