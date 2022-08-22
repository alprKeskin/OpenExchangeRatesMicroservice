package io.github.alprkeskin.openexchangeratesmicroservice.service;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import io.github.alprkeskin.openexchangeratesmicroservice.repositories.CurrencyRatesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class CurrencyPersistanceServiceTest {

    private final CurrencyRatesRepository currencyRatesRepository = Mockito.mock(CurrencyRatesRepository.class);

    @Test
    void given20120708_whenFindCurrencyRatesByIdCalled_thenReturnCurrencyRatesWithTheGivenId() {
        CurrencyPersistanceService currencyPersistanceService = new CurrencyPersistanceService(currencyRatesRepository);
        CurrencyRates currencyRates = new CurrencyRates("1", 365, "USD", new HashMap<String, Double>());
        when(currencyRatesRepository.findById("2012-07-08")).thenReturn(Optional.of(currencyRates));
        Optional<CurrencyRates> result = currencyPersistanceService.findCurrencyRatesById(LocalDate.parse("2012-07-08"));
        assertEquals("1", result.get().getId());
        assertEquals(365, result.get().getTimestamp());
        assertEquals("USD", result.get().getBase());
        assertNotNull(result.get());
    }

//    @Test
//    void saveCurrencyRates() {
//        CurrencyPersistanceService currencyPersistanceService = new CurrencyPersistanceService(currencyRatesRepository);
//        CurrencyRates currencyRates = new CurrencyRates("2010-07-08", 365, "USD", new HashMap<String, Double>());
//        when(currencyRatesRepository.save(any())).thenReturn(currencyRates);
//        LocalDate date = LocalDate.parse("2010-07-08");
//        currencyPersistanceService.saveCurrencyRates(currencyRates, date);
//        when(currencyRatesRepository.findById("2012-07-08")).thenReturn(Optional.of(currencyRates));
//        assertNull(currencyRatesRepository.findById("2010-07-08").get());
//    }
}