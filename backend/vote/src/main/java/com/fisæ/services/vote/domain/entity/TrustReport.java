package com.fisæ.services.vote.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * Represent the trustworthy-ness of a media
 */
@Entity
@Data
@NoArgsConstructor
public class TrustReport {

    /**
     * Media rated by this report
     */
    @OneToOne(mappedBy = "trustReport")
    private Media media;

    /**
     * Id of the media
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id = 0L;

    /**
     * Rating of how blindly the media can be trusted, in percentages
     */
    private float rate = 50;

}
