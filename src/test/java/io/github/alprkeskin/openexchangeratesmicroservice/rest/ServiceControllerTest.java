package io.github.alprkeskin.openexchangeratesmicroservice.rest;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.junit.Assert.*;

public class ServiceControllerTest extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceControllerTest.class);

    private <T> T ServiceControllerTestHelper(String uri, Class<T> classType) throws Exception {
        // initialize mvc
        setUp();
        // send get request to the given uri using our rest controller and save the response (json object) in mvcResult
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        // take the content of the json object inside the mvcResult as a string
        // content is a json object in a string format
        String content = mvcResult.getResponse().getContentAsString();
        LOGGER.info("content: " + content);
        // convert json object (content) to a CurrencyRates java object
        return super.mapFromJson(content, classType);
    }

    @Test
    public void givenLatestRequestWhenGetCurrencyRatesCalledThenGiveTheLatestCurrencyRates() throws Exception {
        String uri = "/api?symbols=USD,TRY,AUD";
        CurrencyRates currencyRates = ServiceControllerTestHelper(uri, CurrencyRates.class);
        // if currencyRates is not null, we pass the test
        assertNotNull(currencyRates);
    }

    @Test
    public void givenHistoricalRequest2012_07_08WhenGetCurrencyRatesCalledThenGiveTheHistoricalCurrencyRates() throws Exception {
        String uri = "/api/2012-07-08.json?symbols=USD,TRY,AUD";
        CurrencyRates currencyRates = ServiceControllerTestHelper(uri, CurrencyRates.class);
        // if currencyRates is not null, we pass the test
        assertNotNull(currencyRates);
    }

    @Test
    public void whenGetCurrenciesCalledThenGiveCurrencies() throws Exception {
        String uri = "/api/currency/currencies";
        Map currencies = ServiceControllerTestHelper(uri, Map.class);
        // if currencyRates is not null, we pass the test
        assertNotNull(currencies);
    }

}
