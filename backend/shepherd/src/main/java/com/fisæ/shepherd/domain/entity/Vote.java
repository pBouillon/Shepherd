package com.fisæ.shepherd.domain.entity;

import lombok.*;

import javax.persistence.*;

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

    /**
     * The trust report of the media that is targeted by this vote
     */
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name="trust_report_id", nullable=false)
    private TrustReport trustReport;

}
