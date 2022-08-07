package io.github.alprkeskin.openexchangeratesmicroservice.service;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.repositories.CurrencyRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/** => @Service annotation creates a bean of the service class. So, you can use @Autowired **/
@Service
public class CurrencyPersistanceService {
    // this will be used to save the data to our mongoDb database
    @Autowired
    private CurrencyRatesRepository currencyRatesRepository;

    // this method finds the currency rates object in our database according to its date. And return it.
    public Optional<CurrencyRates> findCurrencyRatesById(LocalDate localDate) {
        return currencyRatesRepository.findById(localDate.toString());
    }

    // this method saves the currency rates object to our database
    public void saveCurrencyRates(CurrencyRates currencyRatesObject, LocalDate localDate) {
        // set its id
        currencyRatesObject.setId(localDate.toString());
        // save it to the database
        currencyRatesRepository.save(currencyRatesObject);
    }
}

