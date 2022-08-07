package io.github.alprkeskin.openexchangeratesmicroservice.rest;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.service.OpenExchangeRatesMainService;
import io.github.alprkeskin.openexchangeratesmicroservice.utils.DateUtil;
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

    private static final Logger logger = Logger.getLogger(ServiceController.class.getName());

    @GetMapping(value = {"", "/{requestedDate}"})
    public ResponseEntity<CurrencyRates> getResponse(
            @PathVariable(value = "requestedDate", required = false) String requestedDate,
            @RequestParam(value = "symbols", required = false, defaultValue = "XXX") String symbols) {

        logger.log(Level.INFO, "ServiceController::getResponse");
        LocalDate desiredDate = requestedDate == null ? LocalDate.now(): DateUtil.getLocalDate(requestedDate);
        return openExchangeRatesMainService.getResponse(desiredDate, symbols);
    }
}



