package io.github.alprkeskin.openexchangeratesmicroservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "alprkeskin.consumed-urls")
public class CurrencyRatesUrlProperties {
    private String api;
}
