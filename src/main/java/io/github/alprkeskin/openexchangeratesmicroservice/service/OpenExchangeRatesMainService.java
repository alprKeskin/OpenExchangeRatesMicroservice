package io.github.alprkeskin.openexchangeratesmicroservice.service;


import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class OpenExchangeRatesMainService {
    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private CurrencyPersistanceService currencyPersistanceService;


    public ResponseEntity<CurrencyRates> getResponse(LocalDate date, String symbols) {

        // try to take the currency rates of the related date from database
        Optional<CurrencyRates> currencyRates = currencyPersistanceService.findCurrencyRatesById(date);
        // if there is no any current rates object of the related date in the database
        if (!currencyRates.isPresent()) {
            // pull the current rates of related date from open exchange rates website
            CurrencyRates currencyRatesOfRelatedDate = exchangeRatesService.getCurrencyRates(date, symbols);
            // save related date's current rates to the database
            currencyPersistanceService.saveCurrencyRates(currencyRatesOfRelatedDate, date);
            // give a response to the user
            return ok(currencyRatesOfRelatedDate);
        }
        // if there exist a current rates object of related date in the database
        // return the currency rates object of related date from the database
        // currencyRates.get() ==> currencyRate without optional
        return ok(currencyRates.get());
    }
}
