package io.github.alprkeskin.openexchangeratesmicroservice.service;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.Map;


@Service
public class ExchangeRatesService {

    ExchangeRatesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Value("${alprkeskin.consumed-urls.api}")
    private String URL;

    @Value("${query-parameter.app_id}")
    private String app_id;

    public CurrencyRates getCurrencyRates(LocalDate date, String symbols) {
        return restTemplate.getForObject(getUri(date, symbols), CurrencyRates.class);
    }

    public Map getCurrencies() {
        return restTemplate.getForObject(getUri(), Map.class);
    }

    public URI getUri() {
        return URI.create(URL + "currencies.json");
    }

    public URI getUri(LocalDate date, String symbols) {
        String endpoint = date.isEqual(LocalDate.now()) ? "latest.json" : "historical/" + date + ".json";
        String symbolsQueryParameter = "XXX".equals(symbols) ? "": "&symbols=" + symbols;
        return URI.create(URL + endpoint + "?" + "app_id=" + app_id + symbolsQueryParameter);
    }

}



