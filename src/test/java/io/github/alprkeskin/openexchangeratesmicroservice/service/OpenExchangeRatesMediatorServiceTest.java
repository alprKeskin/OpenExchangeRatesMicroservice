package io.github.alprkeskin.openexchangeratesmicroservice.service;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.repositories.CurrencyRatesRepository;
import io.github.alprkeskin.openexchangeratesmicroservice.service.exchangeRates.ExchangeRatesMediatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OpenExchangeRatesMediatorServiceTest {

    private final String TRY_NAME = "Turkish Lira";
    private final String USD_NAME = "United States Dollar";

    private OpenExchangeRatesMediatorService openExchangeRatesMediatorService;
    private ExchangeRatesMediatorService exchangeRatesMediatorService;
    private CurrencyPersistanceService currencyPersistanceService;

    @BeforeEach
    private void instantiateFields() {
        exchangeRatesMediatorService = Mockito.mock(ExchangeRatesMediatorService.class);
        currencyPersistanceService = Mockito.mock(CurrencyPersistanceService.class);
        openExchangeRatesMediatorService = new OpenExchangeRatesMediatorService(exchangeRatesMediatorService, currencyPersistanceService);
    }


    @Test
    void givenCurrentDate_whenGetCurrencyRatesCalled_returnLatestCurrencyRates() {
        // create mocks of database repository for database methods
        CurrencyRatesRepository repository = Mockito.mock(CurrencyRatesRepository.class);
        // create a currency rates object to return as a default object
        CurrencyRates defaultCurrencyRates = new CurrencyRates("1", 365, "USD", new HashMap<String, Double>());
        // set default behaviors for database methods (no need to test them)
        when(repository.findById(any())).thenReturn(Optional.of(defaultCurrencyRates));

        // assign parameters
        LocalDate date = LocalDate.now();
        String symbols = "USD,TRY,AUD";

        // set default behaviors for database methods
        when(exchangeRatesMediatorService.getCurrencyRates(date, symbols)).thenReturn(defaultCurrencyRates);

        // get your result
        CurrencyRates currencyRates = openExchangeRatesMediatorService.getCurrencyRates(date, symbols);

        // assert your statements
        assertNotNull(currencyRates);
    }



//    @Test
//    void getCurrencies() {
//        // get your result
//        Map currencies = openExchangeRatesMainService.getCurrencies();
//
//        // assert your statements
//        assertNotNull(currencies);
//        assertEquals(TRY_NAME, currencies.get("TRY"));
//        assertEquals(USD_NAME, currencies.get("USD"));
//    }
}