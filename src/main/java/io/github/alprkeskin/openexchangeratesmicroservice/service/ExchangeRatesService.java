package io.github.alprkeskin.openexchangeratesmicroservice.service;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.properties.CurrencyRatesUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;


@Service
public class ExchangeRatesService {

    @Autowired
    private CurrencyRatesUrlProperties urlProperties;

    // it autowires to the bean produced by RestTemplateConfig class under the package "config"
    @Autowired
    private RestTemplate restTemplate;

    /**
    this is the main url that we are going to use
    after this url we can add paths to it
    the last path parameter in the url is the end of the Url
     **/
    @Value("${alprkeskin.consumed-urls.api}")
    private String URL;


    public CurrencyRates getCurrencyRates(LocalDate date, String symbols) {
        return restTemplate.getForObject(getUri(date, symbols), CurrencyRates.class);
    }

    // default values of query parameters
    @Value("${query-parameter.app_id}")
    String app_id;
    @Value("${query-parameter.prettyprint}")
    boolean prettyprint;
    @Value("${query-parameter.show_alternative}")
    boolean show_alternative;

    public URI getUri(LocalDate date, String symbols) {
        // URL: https://openexchangerates.org/api/
        String commonUrl = URL;
        String endpoint;

        // if the desired date is the current date
        if (date.isEqual(LocalDate.now())) {
            // generate a request URI for latest endpoint
            endpoint = "latest.json";
        }
        // if the desired date is not the current date
        else {
            // then generate a request URI for historical endpoint
            endpoint = "historical/" + date.toString() + ".json";
        }

        // initialize http request parses
        String path = commonUrl + endpoint;
        String queryStart = "?";
        String app_idPart = "app_id=" + app_id;
        String symbolsPart;
        if ("XXX".equals(symbols)) {
            symbolsPart = "";
        }
        else {
            symbolsPart = "&symbols=" + symbols;
        }
        String prettyprintPart = "&prettyprint=" + prettyprint;
        String show_alternativePart = "&show_alternative=" + show_alternative;

        return URI.create(path + queryStart + app_idPart + symbolsPart + prettyprintPart + show_alternativePart);
    }
}



