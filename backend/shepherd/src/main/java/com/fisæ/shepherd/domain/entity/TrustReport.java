package com.fisæ.shepherd.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @ToString.Exclude
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
    private float rate = 50f;

    /**
     * Collection of the votes that this media has received
     */
    @ToString.Exclude
    @OneToMany(mappedBy="trustReport")
    private Set<Vote> votes = new HashSet<>();

}
