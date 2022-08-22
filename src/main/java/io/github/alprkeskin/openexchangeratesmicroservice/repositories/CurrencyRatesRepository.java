package io.github.alprkeskin.openexchangeratesmicroservice.repositories;

import io.github.alprkeskin.openexchangeratesmicroservice.model.CurrencyRates;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRatesRepository
        extends MongoRepository<CurrencyRates, String> {
}
