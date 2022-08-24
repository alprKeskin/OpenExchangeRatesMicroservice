package io.github.alprkeskin.openexchangeratesmicroservice.service.exchangeRates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CurrenciesServiceTest {

    private CurrenciesService currenciesService;

    @BeforeEach
    void initializeExchangeRatesService() {
        currenciesService = new CurrenciesService();
        ReflectionTestUtils.setField(currenciesService, "app_id", "8749ae82dbde4276a7554e13d03ad71e");
        ReflectionTestUtils.setField(currenciesService, "URL", "https://openexchangerates.org/api/");
    }

    @Test
    void whenGetCurrenciesCalledThenReturnCurrencies() {
        Map currencies = currenciesService.getCurrencies();
        assertNotNull(currencies);
        assertEquals("Turkish Lira", currencies.get("TRY"));
    }

    @Test
    void whenGetUriCalledThenReturnUriForCurrencies() {
        URI expected = URI.create("https://openexchangerates.org/api/currencies.json");
        assertEquals(expected, currenciesService.getUri());
    }
}