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
     * id of the media to retrieve
     */
    @Min(Media.ID_MIN_VALUE)
    public Long id = Media.ID_MIN_VALUE;

}
