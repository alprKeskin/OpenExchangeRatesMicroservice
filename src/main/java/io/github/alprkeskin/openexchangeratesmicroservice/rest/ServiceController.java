package io.github.alprkeskin.openexchangeratesmicroservice.rest;

import io.github.alprkeskin.openexchangeratesmicroservice.model.LatestEndFormat;
import io.github.alprkeskin.openexchangeratesmicroservice.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class ServiceController {
    @Autowired
    private ExchangeRatesService service;

    @GetMapping("/{apiEndpoint}")
    public ResponseEntity<LatestEndFormat> getResponse(
            @PathVariable("apiEndpoint") String apiEndpoint,
            @RequestParam(value = "app_id", required = true) String app_id,
            @RequestParam(value = "base", required = false, defaultValue = "USD") String base,
            @RequestParam(value = "symbols", required = false, defaultValue = "XXX") String symbols,
            @RequestParam(value = "prettyprint", required = false, defaultValue = "false") boolean prettyprint,
            @RequestParam(value = "show_alternative", required = false, defaultValue = "false") boolean show_alternative) {
        System.out.println("--------- ServiceController::getResponse ---------");
        System.out.println("int apiEndpoint: " + apiEndpoint);
        System.out.println("String appId: " + app_id);
        System.out.println("String base: " + base);
        System.out.println("String symbols: " + symbols);
        System.out.println("boolean prettyprint: " + prettyprint);
        System.out.println("boolean show_alternative: " + show_alternative);

        if (!base.equals("USD")) {
            throw new RuntimeException("Base cannot be changed in unlimited plan!");
        }
        else {
            return ok(service.getAndSaveLatest(apiEndpoint, app_id, base, symbols, prettyprint, show_alternative));
        }
    }
}
