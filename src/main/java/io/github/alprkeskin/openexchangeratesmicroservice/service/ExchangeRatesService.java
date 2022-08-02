package io.github.alprkeskin.openexchangeratesmicroservice.service;

import io.github.alprkeskin.openexchangeratesmicroservice.model.LatestEndFormat;
import io.github.alprkeskin.openexchangeratesmicroservice.properties.LatestEndUrlProperties;
import io.github.alprkeskin.openexchangeratesmicroservice.repositories.LatestEndFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ExchangeRatesService {

    // it is used to use a string
    // its value is injected in LatestEndUrlProperties class under the package "properties"
    @Autowired
    private LatestEndUrlProperties urlProperties;

    // it autowires to the bean produced by RestTemplateConfig class under the package "config"
    @Autowired
    private RestTemplate restTemplate;

    // this is the main url that we are going to use
    // after this url we can add paths to it
    // the last path parameter in the url is the end of the Url
    @Value("${alprkeskin.consumed-urls.api}")
    private String URL;


    @Autowired
    private LatestEndFormatRepository latestEndFormatRepository;


    public LatestEndFormat getLatest(String apiEndpoint, String app_id, String base, String symbols, boolean prettyprint, boolean show_alternative) {
        LatestEndFormat latest = restTemplate.getForObject(getUri(apiEndpoint, app_id, base, symbols, prettyprint, show_alternative), LatestEndFormat.class);
        latestEndFormatRepository.save(latest);
        return latest;
    }

    public URI getUri(String apiEndpoint, String app_id, String base, String symbols, boolean prettyprint, boolean show_alternative) {
        // String url = readFromValue ? URL : urlProperties.getApi();
        String url = URL;
        System.out.println("--------- ExchangeRatesService::getUri ---------");
        System.out.println("Returned URI: " +
                url + apiEndpoint + "?" + "app_id=" + app_id +
                "&" + "base=" + base +
                "&" + "symbols=" + symbols +
                "&" + "prettyprint=" + prettyprint +
                "&" + "show_alternative=" + show_alternative);

        // initialize http request parses
        String path = url + apiEndpoint;
        String queryStart = "?";
        String app_idPart = "app_id=" + app_id;
        String basePart = "&base=" + base;
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
