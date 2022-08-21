package io.github.alprkeskin.openexchangeratesmicroservice.service;


import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;


@Service
public class OpenExchangeRatesMainService {

    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private CurrencyPersistanceService currencyPersistanceService;

    OpenExchangeRatesMainService(ExchangeRatesService exchangeRatesService, CurrencyPersistanceService currencyPersistanceService) {
        this.exchangeRatesService = exchangeRatesService;
        this.currencyPersistanceService = currencyPersistanceService;
    }

    public CurrencyRates getCurrencyRates(LocalDate date, String symbols) {
        Optional<CurrencyRates> currencyRates = currencyPersistanceService.findCurrencyRatesById(date);
        if (currencyRates.isEmpty()) {
            CurrencyRates currencyRatesOfRelatedDate = exchangeRatesService.getCurrencyRates(date, symbols);
            currencyPersistanceService.saveCurrencyRates(currencyRatesOfRelatedDate, date);
            return currencyRatesOfRelatedDate;
        }
        return currencyRates.get();
    }

    public Map getCurrencies() {
        return exchangeRatesService.getCurrencies();
    }

}
