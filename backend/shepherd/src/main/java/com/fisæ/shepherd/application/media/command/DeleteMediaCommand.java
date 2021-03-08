package com.fisæ.shepherd.application.media.command;

import com.fisæ.shepherd.domain.entity.Media;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Command to delete an existing {@link Media}
 */
@Data
public class DeleteMediaCommand {

    /**
     * Id of the media to delete
     */
    @Min(Media.ID_MIN_VALUE)
    private Long id = Media.ID_MIN_VALUE;

}
