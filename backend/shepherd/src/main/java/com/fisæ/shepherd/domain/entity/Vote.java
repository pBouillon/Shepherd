package com.fisæ.shepherd.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represent a vote
 *
 * A vote is an insight provided by a user regarding a media, indicating whether it is trustworthy or not
 */
@Entity
@Data
@NoArgsConstructor
public class Vote {

    /**
     * Id of the vote
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id = 0L;

    /**
     * User's opinion regarding the media
     */
    private boolean isTrustworthy = false;

}
