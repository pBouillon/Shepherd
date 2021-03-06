package com.fisæ.shepherd.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

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
     * Creation date of the media, represented in UTC
     *
     * For more details related to the way its stored, see: https://stackoverflow.com/a/62307301/6152689
     */
    private Instant creationDate;

    /**
     * Id of the media
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Media brand name
     */
    private String name;

}
