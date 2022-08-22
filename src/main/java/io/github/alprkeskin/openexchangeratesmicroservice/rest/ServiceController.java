package io.github.alprkeskin.openexchangeratesmicroservice.rest;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.service.OpenExchangeRatesMainService;
import io.github.alprkeskin.openexchangeratesmicroservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

/** The methods of this class automatically run according to the given path when a http request is sent **/
@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    private OpenExchangeRatesMainService openExchangeRatesMainService;

    @GetMapping(value = {"", "/{requestedDate}"})
    public ResponseEntity<CurrencyRates> getCurrencyRates(
            @PathVariable(value = "requestedDate", required = false) String requestedDate,
            @RequestParam(value = "symbols", required = false, defaultValue = "XXX") String symbols) {

        LocalDate desiredDate = requestedDate == null ? LocalDate.now() : DateUtil.getLocalDate(requestedDate);
        return ResponseEntity.ok(openExchangeRatesMainService.getCurrencyRates(desiredDate, symbols));
    }

    @GetMapping(value = "currency/currencies")
    public ResponseEntity<Map> getCurrencies() {
        return ResponseEntity.ok(openExchangeRatesMainService.getCurrencies());
    }
}




