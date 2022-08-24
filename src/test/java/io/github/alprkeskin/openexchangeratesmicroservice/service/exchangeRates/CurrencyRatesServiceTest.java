package io.github.alprkeskin.openexchangeratesmicroservice.service.exchangeRates;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.service.exchangeRates.abstractClasses.ExchangeRatesAbstractClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRatesServiceTest {

    private CurrencyRatesService currencyRatesService;

    @BeforeEach
    void initializeExchangeRatesService() {
        currencyRatesService = new CurrencyRatesService();
        ReflectionTestUtils.setField(currencyRatesService, "app_id", "8749ae82dbde4276a7554e13d03ad71e");
        ReflectionTestUtils.setField(currencyRatesService, "URL", "https://openexchangerates.org/api/");
    }

    @Test
    void givenCurrentDateWhenGetCurrencyRatesCalledThenReturnCurrencyRatesOfTheCurrentDate() {
        CurrencyRates currencyRates = currencyRatesService.getCurrencyRates(LocalDate.now(), "USD,TRY,AUD");
        assertNotNull(currencyRates);
    }

    @Test
    void givenTheCurrentDateWhenGetUriCalledThenReturnUriForLatestCurrencyRates() {
        String expected = URI.create("https://openexchangerates.org/api/latest.json").toString();
        String actual = (currencyRatesService.getUri(LocalDate.now(), "USD,TRY,AUD")).toString().substring(0,45);
        assertEquals(expected, actual);
    }

}