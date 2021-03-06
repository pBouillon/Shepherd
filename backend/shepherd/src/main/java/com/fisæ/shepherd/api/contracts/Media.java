package com.fisæ.shepherd.api.contracts;

import lombok.Data;

import java.time.Instant;

/**
 * Media DTO, served by the API
 */
@Data
public class Media {

    /**
     * Creation date of the media, represented as UTC
     */
    private Instant creationDate;

    /**
     * Id of the media
     */
    private Long id;

    /**
     * Media brand name
     */
    private String name;

}
