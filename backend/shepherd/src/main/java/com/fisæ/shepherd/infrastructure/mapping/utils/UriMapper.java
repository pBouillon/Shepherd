package com.fisæ.shepherd.infrastructure.mapping.utils;

import org.mapstruct.Mapper;

import java.net.URI;
import java.util.Optional;

/**
 * {@link URI} mapping profile
 */
@Mapper(componentModel = "spring")
public interface UriMapper {

    /**
     * Map an optional URI to an optional String
     *
     * @param value Optional URI to map
     *
     * @return The resulting optional String
     */
    default Optional<String> toOptionalString(Optional<URI> value) {
        return value.map(URI::toString);
    }

    /**
     * Map an optional String to an optional URI
     *
     * @param value Optional String to map
     *
     * @return The resulting optional URI
     */
    default Optional<URI> toOptionalUri(Optional<String> value) {
        return value.map(rawUri -> URI.create(rawUri.trim()));
    }

    /**
     * Map a URI to a String
     *
     * @param value URI to map
     *
     * @return The resulting String
     */
    default String toString(URI value) {
        return value.toString();
    }

    /**
     * Map a String to a URI
     *
     * @param value String to map
     *
     * @return The resulting URI
     */
    default URI toUri(String value) {
        return URI.create(value.trim());
    }

}
