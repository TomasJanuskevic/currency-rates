package com.currencyrates.service;

import com.currencyrates.dto.UserInput;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class UserService {

    private UserInput userInput = new UserInput();

    public void initForCurrencyHistory(UserInput userInput) {
        this.userInput.setCurrency(userInput.getCurrency());
        this.userInput.setDateFrom(userInput.getDateFrom());
        this.userInput.setDateTo(userInput.getDateTo());
    }

    public void initForCalculator(UserInput userInput, double currencyRate) {
        this.userInput.setCurrency(userInput.getCurrency());
        this.userInput.setCurrencyValue(userInput.getCurrencyValue());
        this.userInput.setCurrencyRate(currencyRate);
    }
}
