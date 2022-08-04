package io.github.alprkeskin.openexchangeratesmicroservice.service;


import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class MainService {
    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private DatabaseService databaseService;


    public ResponseEntity<CurrencyRates> getResponse(String endpoint, String symbols, LocalDate date) {

        // try to take the currency rates of the related date from database
        Optional<CurrencyRates> currencyRates = databaseService.findCurrencyRatesById(date);
        // if there is no any current rates object of the related date in the database
        if (currencyRates.equals(Optional.empty())) {
            // pull the current rates of related date from open exchange rates website
            CurrencyRates currencyRatesOfRelatedDate = exchangeRatesService.getCurrencyRates(endpoint, symbols);
            // save related date's current rates to the database
            databaseService.saveCurrencyRates(currencyRatesOfRelatedDate, date);
            // give a response to the user
            return ok(currencyRatesOfRelatedDate);
        }
        // if there exist a current rates object of related date in the database
        else {
            // return the currency rates object of related date from the database
            // currencyRates.get() ==> currencyRate without optional
            return ok(currencyRates.get());
        }
    }
}
