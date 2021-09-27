package com.currencyrates.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    @Autowired
    private UserService userService;

    public double calculateCurrency() {
        double currencyValue = userService.getUserInput().getCurrencyValue();
        double currencyRate = userService.getUserInput().getCurrencyRate();
        return Math.round(currencyValue * currencyRate * 100.0) / 100.0;
    }

}
