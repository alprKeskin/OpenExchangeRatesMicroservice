package io.github.alprkeskin.openexchangeratesmicroservice.repositories;

import io.github.alprkeskin.openexchangeratesmicroservice.model.LatestEndFormat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LatestEndFormatRepository
        extends MongoRepository<LatestEndFormat, String> {
}
