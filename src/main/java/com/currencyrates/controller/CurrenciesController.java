package com.currencyrates.controller;


import com.currencyrates.dto.UserInput;
import com.currencyrates.service.CalculatorService;
import com.currencyrates.service.CurrencyService;
import com.currencyrates.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


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
    public String currencyHistory(Model model) {
        model.addAttribute("userInput", new UserInput());
        model.addAttribute("currencyList", currencyService.getLatestCurrenciesRate());
        return "currencyHistory";
    }

    @PostMapping("/currencyRates")
    public String currencyHistoryInput(Model model, @ModelAttribute UserInput userInput) {
        log.info("Form submitted with data: " + userInput);
        userService.initForCurrencyHistory(userInput);
        return "redirect:currencyRates";
    }

    @GetMapping("/currencyRates")
    public String currencyRates(Model model) {
        model.addAttribute("userInput", userService.getUserInput());
        model.addAttribute("currencyRates", currencyService.getCurrencyRateHistory());
        return "currencyRates";
    }

    @GetMapping("currencyCalculator")
    public String currencyCalculator(Model model) {
        model.addAttribute("userInput", new UserInput());
        model.addAttribute("result", calculatorService.calculateCurrency());
        model.addAttribute("userInputInformation", userService.getUserInput());
        model.addAttribute("currencyList", currencyService.getLatestCurrenciesRate());
        return "currencyCalculator";
    }

    @PostMapping("currencyCalculator")
    public String currencyCalculatorInput(Model model, @ModelAttribute UserInput userInput) {
        log.info("Form submitted with data: " + userInput);
        userService.initForCalculator(userInput, currencyService.getLatestCurrencyRate(userInput.getCurrency()));
        return "redirect:currencyCalculator";
    }
}
