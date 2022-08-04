package io.github.alprkeskin.openexchangeratesmicroservice.rest;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

// Bir http isteği geldiğinde bu class'ın method'ları otomatik olarak path'e göre çalışacaktır.
@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    MainService mainService;

    // http://localhost:8082/api/2012-07-10.json?symbols=TRY

    @GetMapping(value = {"", "/{requestedDate}"})
    public ResponseEntity<CurrencyRates> getResponse(
            @PathVariable(value = "requestedDate", required = false) String requestedDate,
            @RequestParam(value = "symbols", required = false, defaultValue = "XXX") String symbols) {

        LocalDate desiredDate;
        String endpoint;

        // if we want to pull the latest currency rates
        if (requestedDate == null) {
            // pull the related date
            desiredDate = LocalDate.now();
            // assign the related endpoint
            endpoint = "latest.json";
        }
        // if we want to pull the historical currency rates
        else {
            // modify the requested date properly
            desiredDate = LocalDate.parse(requestedDate.substring(0,10));
            // if the desired date is in the future of the current date
            if (desiredDate.compareTo(LocalDate.now()) >= 0) {
                // throw a runtime exception
                // after this point the thread terminates
                throw new RuntimeException("Requested date cannot be greater than or equal to today!");
            }
            // assign the related endpoint
            endpoint = requestedDate;
        }

        // return the response
        return mainService.getResponse(endpoint, symbols, desiredDate);
    }
}



