package com.currencyrates.service;

import com.currencyrates.dto.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CalculatorServiceTest {

    private CalculatorService calculatorTestService;
    private UserService userTestService;

    @BeforeEach
    void setUp() {
        userTestService = new UserService();
        calculatorTestService = new CalculatorService(userTestService);
    }

    @Test
    void calculateCurrency() {
        UserInput userInput = new UserInput("USD", "2021-09-01", "2021-09-15", 26, 1.16);
        userTestService.setUserInput(userInput);
        double actualResult = calculatorTestService.calculateCurrency();
        double expectedResult = 30.16;

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calculateCurrencyIfValueIsNull() {
        UserInput userInput = new UserInput("USD", "2021-09-01", "2021-09-15", null, 1.16);
        userTestService.setUserInput(userInput);
        double actualResult = calculatorTestService.calculateCurrency();
        double expectedResult = 0;

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
