package com.currencyrates.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalculatorService {
    @Autowired
    private UserService userService;

    public double calculateCurrency() {
        double currencyRate = userService.getUserInput().getCurrencyRate();
        Integer currencyValue = userService.getUserInput().getCurrencyValue();
        if (currencyValue == null) {
            return 0;
        }
        return Math.round(currencyValue * currencyRate * 100.0) / 100.0;
    }
}
