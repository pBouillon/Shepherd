package com.fisæ.shepherd.application.media.command;

import com.fisæ.shepherd.domain.entity.Media;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Command to update an existing {@link Media}
 */
@Data
public class UpdateMediaCommand {

    /**
     * Media brand name such as "CNN", "Fox News", etc.
     */
    @NotBlank
    @Size(min = Media.NAME_MIN_LENGTH, max = Media.NAME_MAX_LENGTH)
    private String name = "";

}
