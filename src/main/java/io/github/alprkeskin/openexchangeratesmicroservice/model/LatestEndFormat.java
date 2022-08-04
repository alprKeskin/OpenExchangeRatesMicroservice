package io.github.alprkeskin.openexchangeratesmicroservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class LatestEndFormat {
    @Id
    private String id;
    private String disclaimer;
    private String license;
    private int timestamp;
    private String base;
    private Map<String, Double> rates;

    public LatestEndFormat(String disclaimer, String license, int timestamp, String base, Map<String, Double> rates) {
        this.disclaimer = disclaimer;
        this.license = license;
        this.timestamp = timestamp;
        this.base = base;
        this.rates = rates;
    }
}
