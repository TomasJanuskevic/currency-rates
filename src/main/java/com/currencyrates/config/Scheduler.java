package com.currencyrates.config;

import com.currencyrates.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class Scheduler {
    @Autowired
    private CurrencyService currencyService;

    //@Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "0/5 * * * * *")
    private void addCurrencyData() {
        currencyService.addCurrencyList();
        currencyService.addLatestCurrencyRates();
    }

}
