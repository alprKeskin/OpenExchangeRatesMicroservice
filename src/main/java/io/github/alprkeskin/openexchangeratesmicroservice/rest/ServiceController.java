package io.github.alprkeskin.openexchangeratesmicroservice.rest;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.service.OpenExchangeRatesMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/** The methods of this class automatically run according to the given path when a http request is sent **/
@RestController
@RequestMapping("/api")
public class ServiceController {
    @Autowired
    private OpenExchangeRatesMainService openExchangeRatesMainService;

    Logger logger = Logger.getLogger(ServiceController.class.getName());

    @GetMapping(value = {"", "/{requestedDate}"})
    public ResponseEntity<CurrencyRates> getResponse(
            @PathVariable(value = "requestedDate", required = false) String requestedDate,
            @RequestParam(value = "symbols", required = false, defaultValue = "XXX") String symbols) {

        LocalDate desiredDate;

        // if we want to pull the latest currency rates
        if (requestedDate == null) {
            logger.log(Level.INFO, "Latest currency rates has been requested!");
            // pull the related date
            desiredDate = LocalDate.now();
        }
        // if we want to pull the historical currency rates
        else {
            logger.log(Level.INFO, "Historical currency rates has been requested");
            // modify the requested date properly
            desiredDate = LocalDate.parse(requestedDate.substring(0,10));
            // if the desired date is in the future of the current date
            if (desiredDate.compareTo(LocalDate.now()) >= 0) {
                // throw a runtime exception
                // after this point the thread terminates
                throw new RuntimeException("Requested date cannot be greater than or equal to today!");
            }
        }

        // return the response
        return openExchangeRatesMainService.getResponse(desiredDate, symbols);
    }
}



