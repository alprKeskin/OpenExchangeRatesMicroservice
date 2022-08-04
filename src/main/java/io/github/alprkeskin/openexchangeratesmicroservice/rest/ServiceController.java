package io.github.alprkeskin.openexchangeratesmicroservice.rest;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

// Bir http isteği geldiğinde bu class'ın method'ları otomatik olarak path'e göre çalışacaktır.
@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    MainService mainService;

    // latest endpoint
    @GetMapping("/{latestEndpoint}")
    public ResponseEntity<CurrencyRates> getResponseLatest(
            @PathVariable(value = "latestEndpoint") String latestEndpoint,
            @RequestParam(value = "symbols", required = false, defaultValue = "XXX") String symbols) {
        // pull the related date
        LocalDate dateOfToday = LocalDate.now();
        // return the response
        return mainService.getResponse(latestEndpoint, symbols, dateOfToday);
    }

    // historical endpoint
    @GetMapping("historical/{historicalEndpoint}")
    public ResponseEntity<CurrencyRates> getResponseHistorical(
            @PathVariable(value = "historicalEndpoint") String historicalEndpoint,
            @RequestParam(value = "symbols", required = false, defaultValue = "XXX") String symbols){
        // modify the desired day properly
        LocalDate desiredDate = LocalDate.parse(historicalEndpoint.substring(0,10));
        // return the response
        return mainService.getResponse(historicalEndpoint, symbols, desiredDate);
    }
}



