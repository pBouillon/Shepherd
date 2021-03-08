package com.fisæ.shepherd.application.media.query;

import com.fisæ.shepherd.domain.entity.Media;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Query to get a specific {@link Media} stored in Shepherd
 */
@Data
public class GetMediaQuery {

    /**
     * Minimum value of the id of a {@link Media}
     */
    public static final long ID_MIN_VALUE = 1L;

    /**
     * id of the media to retrieve
     */
    @Min(ID_MIN_VALUE)
    public Long id = ID_MIN_VALUE;

}
