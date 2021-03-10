package com.fisæ.shepherd.application.media.contracts;

import lombok.Data;

import java.net.URI;
import java.time.Instant;
import java.util.Optional;

/**
 * Media DTO, served by the API
 */
@Data
public class MediaDto {

    /**
     * Creation date of the media, represented in UTC
     */
    private Instant creationDate;

    /**
     * Media description such as when it was founded, its country, etc.
     */
    private String description;

    /**
     * Id of the media
     */
    private Long id;

    /**
     * Media brand name
     */
    private String name;

    /**
     * External website, referring to the official media's webpage
     */
    private Optional<URI> website;

}
