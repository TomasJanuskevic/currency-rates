package com.currencyrates.config;

import com.currencyrates.dto.CcyNtry;
import com.currencyrates.dto.FxRate;
import com.currencyrates.repository.CcyNtryRepository;
import com.currencyrates.repository.FxRateRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.net.URL;
import java.util.List;


@Configuration
public class CurrencyConfig {
    @Bean
    CommandLineRunner commandLineRunner(CcyNtryRepository ccyNtryRepository, FxRateRepository fxRateRepository) {

        return args -> {
            try {
                ObjectMapper mapper = new XmlMapper();
                URL url = new URL("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrencyList");
                TypeReference<List<CcyNtry>> typeReference = new TypeReference<List<CcyNtry>>(){};
                List<CcyNtry> ccyNtries = mapper.readValue(url, typeReference);
                ccyNtryRepository.saveAll(ccyNtries);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                ObjectMapper mapper = new XmlMapper();
                URL url = new URL("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=LT");
                // URL url = new URL("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRatesForCurrency?tp=LT&ccy=USD&dtFrom=2021-09-20&dtTo=2021-09-25");
                TypeReference<List<FxRate>> typeReference = new TypeReference<List<FxRate>>(){};
                List<FxRate> fxRates = mapper.readValue(url, typeReference);
                fxRateRepository.saveAll(fxRates);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
