package io.github.alprkeskin.openexchangeratesmicroservice.service.exchangeRates;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ExchangeRatesMediatorServiceTest {

    private ExchangeRatesMediatorService exchangeRatesMediatorService;

    private CurrencyRatesService currencyRatesService;

    private CurrenciesService currenciesService;

    CurrencyRates defaultCurrencyRates;

    @BeforeEach
    private void instantiateFields() {
        currencyRatesService = Mockito.mock(CurrencyRatesService.class);
        currenciesService = Mockito.mock(CurrenciesService.class);
        exchangeRatesMediatorService = new ExchangeRatesMediatorService(currencyRatesService, currenciesService);

        defaultCurrencyRates = new CurrencyRates("1", 365, "USD", new HashMap<String, Double>());
    }

    @Test
    void given20170708AndUSDTRY_whenGetCurrencyRatesIsCalled_returnCurrencyRates() {
        LocalDate date = LocalDate.parse("2017-07-08");
        String symbols = "USD,TRY,AUD";
        when(currencyRatesService.getCurrencyRates(any(), any())).thenReturn(defaultCurrencyRates);
        assertNotNull(exchangeRatesMediatorService.getCurrencyRates(date, symbols));
    }

    @Test
    void getCurrencies() {
    }
}