package com.fisæ.shepherd.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

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
    @NonNull private Instant creationDate = Instant.now();

    /**
     * Id of the media
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull private Long id = 0L;

    /**
     * Media brand name
     */
    @NonNull private String name = "";

    /**
     * Create a new media
     *
     * @param name Media brand name such as "CNN", "Fox News", etc.
     */
    public Media(@NonNull String name) {
        this.name = name;
    }

}
