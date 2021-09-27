package com.currencyrates.service;

import com.currencyrates.dto.currencyRate;
import com.currencyrates.dto.Currency;
import com.currencyrates.dto.FxRate;
import com.currencyrates.repository.CurrencyRateRepository;
import com.currencyrates.repository.CurrencyRepository;
import com.currencyrates.repository.FxRateRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrencyService {
    @Autowired
    private final CurrencyRepository currencyRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final CurrencyRateRepository currencyRateRepository;
    @Autowired
    private final FxRateRepository fxRateRepository;

    public List<Currency> getCurrencyList() {
        return currencyRepository.findAll();
    }

    public List<FxRate> getCurrencyRateHistory() {
        String currency = userService.getUserInput().getCurrency();
        String dataFrom = userService.getUserInput().getDateFrom();
        String dataTo = userService.getUserInput().getDateTo();
        List<FxRate> fxRates = new ArrayList<>();
        try {
            ObjectMapper mapper = new XmlMapper();
            URL url = new URL("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRatesForCurrency?tp=LT&ccy=" +
                    currency + "&dtFrom=" + dataFrom + "&dtTo=" + dataTo);
            TypeReference<List<FxRate>> typeReference = new TypeReference<>() {
            };
            fxRates = mapper.readValue(url, typeReference);
            return fxRates;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fxRates;
    }

    public double getLatestCurrencyRate(String currency) {
        return currencyRateRepository.findByCurrencyCode(currency).getRate();
    }

    public List<currencyRate> getLatestCurrenciesRate() {
        List<Currency> currencyList = getCurrencyList();
        List<currencyRate> currencyRatesList = currencyRateRepository.findAll();
        List<currencyRate> filteredListWithFullNames = currencyRatesList.stream()
                .filter(c -> !c.getCurrencyCode().equals("EUR")).collect(Collectors.toList());

        filteredListWithFullNames.forEach(c -> c.setCurrencyName(
                currencyList.stream()
                        .filter(l -> l.getCurrencyCode().equals(c.getCurrencyCode()))
                        .map(Currency::getCurrencyName)
                        .findAny().orElse("")));

        return filteredListWithFullNames;
    }

    public void addCurrencyList() {
        try {
            ObjectMapper mapper = new XmlMapper();
            URL url = new URL("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrencyList");
            TypeReference<List<Currency>> typeReference = new TypeReference<>() {
            };
            List<Currency> ccyNtries = mapper.readValue(url, typeReference);
            currencyRepository.saveAll(ccyNtries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLatestCurrencyRates() {
        try {
            ObjectMapper mapper = new XmlMapper();
            URL url = new URL("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=LT");
            TypeReference<List<FxRate>> typeReference = new TypeReference<>() {
            };
            List<FxRate> fxRates = mapper.readValue(url, typeReference);
            fxRateRepository.saveAll(fxRates);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
