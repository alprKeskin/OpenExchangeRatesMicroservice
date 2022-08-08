package io.github.alprkeskin.openexchangeratesmicroservice.rest;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.service.OpenExchangeRatesMainService;
import io.github.alprkeskin.openexchangeratesmicroservice.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/** The methods of this class automatically run according to the given path when a http request is sent **/
@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    private OpenExchangeRatesMainService openExchangeRatesMainService;

    /**
     * org.slf4j.Logger is an interface
     * So, we coded to interface (good approach)
     **/
    private final static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @GetMapping(value = {"", "/{requestedDate}"})
    public ResponseEntity<CurrencyRates> getResponse(
            @PathVariable(value = "requestedDate", required = false) String requestedDate,
            @RequestParam(value = "symbols", required = false, defaultValue = "XXX") String symbols) {

        logger.info("ServiceController::getResponse");
        LocalDate desiredDate = requestedDate == null ? LocalDate.now(): DateUtil.getLocalDate(requestedDate);
        return openExchangeRatesMainService.getResponse(desiredDate, symbols);
    }
}




