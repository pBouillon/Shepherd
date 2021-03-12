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
        return value.isPresent()
                ? Optional.of(value.get().toString())
                : Optional.empty();
    }

    /**
     * Map an optional String to an optional URI
     *
     * @param value Optional String to map
     *
     * @return The resulting optional URI
     */
    default Optional<URI> toOptionalUri(Optional<String> value) {
        return value.isPresent()
                ? Optional.of(URI.create(value.get().trim()))
                : Optional.empty();
    }

}
