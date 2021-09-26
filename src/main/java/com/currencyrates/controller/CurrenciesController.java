package com.currencyrates.controller;


import com.currencyrates.dto.UserInput;
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


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userInput", new UserInput());
        model.addAttribute("currencyList", currencyService.getCurrencyList());
        return "index";
    }

    @PostMapping("/select")
    public String postSelectForm(Model model, @ModelAttribute UserInput userInput) {
        log.info("Form submitted with data: " + userInput);
        userService.setUserInput(userInput);
        return "redirect:currencyRates";
    }

    @GetMapping("/currencyRates")
    public String currencyRates(Model model){
        model.addAttribute("userInput", userService.getUserInput());
        return "currencyRates";
    }
}
