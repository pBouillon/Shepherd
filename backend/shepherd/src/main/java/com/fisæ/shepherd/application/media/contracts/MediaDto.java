package com.fisæ.shepherd.application.media.contracts;

import lombok.Data;

import java.time.Instant;

/**
 * Media DTO, served by the API
 */
@Data
public class MediaDto {

    /**
     * Creation date of the media, represented in UTC
     */
    private Instant creationDate = Instant.now();

    /**
     * Id of the media
     */
    private Long id = 0L;

    /**
     * Media brand name
     */
    private String name = "";

}
