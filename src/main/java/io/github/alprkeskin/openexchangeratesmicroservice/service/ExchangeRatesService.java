package io.github.alprkeskin.openexchangeratesmicroservice.service;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.properties.CurrencyRatesUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Service
public class ExchangeRatesService {

    // it is used to use a string
    // its value is injected in LatestEndUrlProperties class under the package "properties"
    @Autowired
    private CurrencyRatesUrlProperties urlProperties;

    // it autowires to the bean produced by RestTemplateConfig class under the package "config"
    @Autowired
    private RestTemplate restTemplate;

    // this is the main url that we are going to use
    // after this url we can add paths to it
    // the last path parameter in the url is the end of the Url
    @Value("${alprkeskin.consumed-urls.api}")
    private String URL;

    // default values of query parameters
    @Value("${query-parameter.app_id}")
    String app_id;
    @Value("${query-parameter.base}")
    String base;
    @Value("${query-parameter.prettyprint}")
    boolean prettyprint;
    @Value("${query-parameter.show_alternative}")
    boolean show_alternative;

    public CurrencyRates getCurrencyRates(String endpoint, String symbols) {
        return restTemplate.getForObject(getUri(endpoint, symbols), CurrencyRates.class);
    }

    public URI getUri(String endpoint, String symbols) {
        // URL: https://openexchangerates.org/api/
        String commonUrl = URL;

        // initialize http request parses
        // if we want to reach historical endpoint
        if (!endpoint.equals("latest.json")) {
            // update the endpoint such that apiEndpoint = "historical/2012-07-08.json"
            endpoint = "historical/" + endpoint;
        }
        String path = commonUrl + endpoint;
        String queryStart = "?";
        String app_idPart = "app_id=" + app_id;
        String symbolsPart;
        if (symbols.equals("XXX")) {
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



