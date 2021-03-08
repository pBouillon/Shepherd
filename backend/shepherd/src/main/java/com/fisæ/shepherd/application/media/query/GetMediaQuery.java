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
    @Min(1)
    public Long id = 0L;

}
