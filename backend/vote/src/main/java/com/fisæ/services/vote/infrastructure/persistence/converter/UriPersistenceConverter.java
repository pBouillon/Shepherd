package com.fisæ.services.vote.infrastructure.persistence.converter;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.net.URI;
import java.util.Optional;

/**
 * JPA attribute converter, converting an optional URI to a String back and forth
 */
@Converter(autoApply = true)
public class UriPersistenceConverter implements AttributeConverter<Optional<URI>, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToDatabaseColumn(Optional<URI> entityValue) {
        return entityValue.isEmpty()
                ? ""
                : entityValue.get().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<URI> convertToEntityAttribute(String databaseValue) {
        return StringUtils.hasLength(databaseValue)
                ? Optional.of(URI.create(databaseValue.trim()))
                : Optional.empty();
    }

}
