package com.currencyrates.controller;


import com.currencyrates.dto.UserInput;
import com.currencyrates.service.CalculatorService;
import com.currencyrates.service.CurrencyService;
import com.currencyrates.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@Log
public class CurrenciesController {
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("currencyList", currencyService.getLatestCurrenciesRate());
        return "index";
    }

    @GetMapping("/currencyHistory")
    public String currencyHistory(UserInput userInput, Model model) {
        model.addAttribute("userInput", userInput);
        model.addAttribute("currencyList", currencyService.getLatestCurrenciesRate());
        return "currencyHistory";
    }

    @GetMapping("/currencyRates")
    public String currencyRates(Model model) {
        model.addAttribute("userInput", userService.getUserInput());
        model.addAttribute("currencyRates", currencyService.getCurrencyRateHistory());
        return "currencyRates";
    }

    @GetMapping("currencyCalculator")
    public String currencyCalculator(Model model, UserInput userInput, boolean showResult) {
        model.addAttribute("showResult", showResult);
        model.addAttribute("userInput", userInput);
        model.addAttribute("result", calculatorService.calculateCurrency());
        model.addAttribute("userInputInformation", userService.getUserInput());
        model.addAttribute("currencyList", currencyService.getLatestCurrenciesRate());
        return "currencyCalculator";
    }

    @PostMapping("/currencyRates")
    public String currencyHistoryInput(@Valid UserInput userInput, BindingResult result, Model model) {
        log.info("Form submitted with data: " + userInput);
        userService.initForCurrencyHistory(userInput);
        if (result.hasErrors()) {
            return currencyHistory(userInput, model);
        }
        return currencyRates(model);
    }

    @PostMapping("currencyCalculator")
    public String currencyCalculatorInput(@Valid UserInput userInput, BindingResult result, Model model) {
        log.info("Form submitted with data: " + userInput);
        userService.initForCalculator(userInput, currencyService.getLatestCurrencyRate(userInput.getCurrency()));
        if (result.hasErrors()) {
            return currencyCalculator(model, userInput, false);
        }
        return currencyCalculator(model, userInput, true);
    }
}
