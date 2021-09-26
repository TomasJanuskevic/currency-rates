package com.currencyrates.service;

import com.currencyrates.dto.CcyAmt;
import com.currencyrates.dto.CcyNtry;
import com.currencyrates.dto.FxRate;
import com.currencyrates.repository.CcyAmtRepository;
import com.currencyrates.repository.CcyNtryRepository;
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

@Service
@AllArgsConstructor
public class CurrencyService {
    @Autowired
    private final CcyNtryRepository ccyNtryRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final CcyAmtRepository ccyAmtRepository;
    @Autowired
    private final FxRateRepository fxRateRepository;

    public List<CcyNtry> getCurrencyList() {
        return ccyNtryRepository.findAll();
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
        return ccyAmtRepository.findByCcy(currency).getAmt();
    }

    public List<CcyAmt> getLatestCurrenciesRate() {
        List<CcyAmt> currencyList = ccyAmtRepository.findAll();
        List<CcyAmt> currencyListWithoutEur = new ArrayList<>();
        for (int i = 0; i < currencyList.size(); i++) {
            if (i % 2 != 0) {
                currencyListWithoutEur.add(currencyList.get(i));
            }
        }
        return currencyListWithoutEur;
    }

    public void addCurrencyList(){
        try {
            ObjectMapper mapper = new XmlMapper();
            URL url = new URL("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrencyList");
            TypeReference<List<CcyNtry>> typeReference = new TypeReference<>() {
            };
            List<CcyNtry> ccyNtries = mapper.readValue(url, typeReference);
            ccyNtryRepository.saveAll(ccyNtries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLatestCurrencyRates(){
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
