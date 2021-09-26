package com.currencyrates.config;

import com.currencyrates.service.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CurrencyConfig {
    @Bean
    CommandLineRunner commandLineRunner(CurrencyService currencyService) {

        return args -> {
            currencyService.addLatestCurrencyRates();
            currencyService.addCurrencyList();
        };
    }
}
