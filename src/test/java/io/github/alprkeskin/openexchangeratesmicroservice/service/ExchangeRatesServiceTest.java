package io.github.alprkeskin.openexchangeratesmicroservice.service;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class ExchangeRatesServiceTest {

    private ExchangeRatesService exchangeRatesService;

    @BeforeEach
    void initializeExchangeRatesService() {
        exchangeRatesService = new ExchangeRatesService(new RestTemplate());
        ReflectionTestUtils.setField(exchangeRatesService, "app_id", "8749ae82dbde4276a7554e13d03ad71e");
        ReflectionTestUtils.setField(exchangeRatesService, "URL", "https://openexchangerates.org/api/");
    }

    @Test
    void givenCurrentDateWhenGetCurrencyRatesCalledThenReturnCurrencyRatesOfTheCurrentDate() {
        CurrencyRates currencyRates = exchangeRatesService.getCurrencyRates(LocalDate.now(), "USD,TRY,AUD");
        assertNotNull(currencyRates);
    }

    @Test
    void whenGetCurrenciesCalledThenReturnCurrencies() {
        Map currencies = exchangeRatesService.getCurrencies();
        assertNotNull(currencies);
        assertEquals("Turkish Lira", currencies.get("TRY"));
    }

    @Test
    void whenGetUriCalledThenReturnUriForCurrencies() {
        URI expected = URI.create("https://openexchangerates.org/api/currencies.json");
        assertEquals(expected, exchangeRatesService.getUri());
    }

    @Test
    void givenTheCurrentDateWhenGetUriCalledThenReturnUriForLatestCurrencyRates() {
        String expected = URI.create("https://openexchangerates.org/api/latest.json").toString();
        String actual = (exchangeRatesService.getUri(LocalDate.now(), "USD,TRY,AUD")).toString().substring(0,45);
        assertEquals(expected, actual);
    }
}