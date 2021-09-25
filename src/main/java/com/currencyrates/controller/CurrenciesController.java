package com.currencyrates.controller;

import com.currencyrates.dto.CcyNtry;
import com.currencyrates.dto.FxRate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.List;


@RestController
public class CurrenciesController {


    @GetMapping(value = "/getCurrencies")
    public String saveCurrencies() {

        try {
            ObjectMapper mapper = new XmlMapper();
            URL url = new URL("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrencyList");
            TypeReference<List<CcyNtry>> typeReference = new TypeReference<List<CcyNtry>>(){};
            List<CcyNtry> ntries = mapper.readValue(url, typeReference);
            System.out.println(ntries);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper mapper = new XmlMapper();
            URL url = new URL("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRatesForCurrency?tp=LT&ccy=USD&dtFrom=2021-09-20&dtTo=2021-09-25");
            TypeReference<List<FxRate>> typeReference = new TypeReference<List<FxRate>>(){};
            List<FxRate> fxRates = mapper.readValue(url, typeReference);
            System.out.println(fxRates);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "save";

    }

}
