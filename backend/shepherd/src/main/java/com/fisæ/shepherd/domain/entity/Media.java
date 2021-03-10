package com.fisæ.shepherd.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.URI;
import java.time.Instant;
import java.util.Optional;

/**
 * Represent a media
 *
 * A media is a news company (e.g. CNN) or any other source that publish articles or news (Medium, etc.)
 */
@Entity
@Data
@NoArgsConstructor
public class Media {

    /**
     * Minimum value of the media id
     */
    public static final long ID_MIN_VALUE = 1L;

    /**
     * Maximum length of the media description
     */
    public static final int DESCRIPTION_MAX_LENGTH = 256;

    /**
     * Minimum length of the media description
     */
    public static final int DESCRIPTION_MIN_LENGTH = 16;

    /**
     * Maximum length of the media name
     */
    public static final int NAME_MAX_LENGTH = 32;

    /**
     * Minimum length of the media name
     */
    public static final int NAME_MIN_LENGTH = 3;

    /**
     * Creation date of the media, represented in UTC
     *
     * For more details related to the way its stored, see: https://stackoverflow.com/a/62307301/6152689
     */
    @NonNull
    private Instant creationDate = Instant.now();

    /**
     * Id of the media
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id = 0L;

    /**
     * Media description such as when it was founded, its country, etc.
     */
    @NonNull
    private String description = "";

    /**
     * Media description such as when it was founded, its country, etc.
     */
    @NonNull
    private String description = "";

    /**
     * Media brand name
     */
    @NonNull
    private String name = "";

    /**
     * External website, referring to the official media's webpage
     */
    private Optional<URI> website = Optional.empty();

    /**
     * Create a new media
     *
     * @param name Media brand name such as "CNN", "Fox News", etc.
     */
    public Media(@NonNull String name) {
        this.name = name;
    }

}
