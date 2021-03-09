package com.fisæ.shepherd.application.administrator.command;

import com.fisæ.shepherd.domain.entity.Administrator;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Command to create a new {@link Administrator}
 */
@Data
public class CreateAdministratorCommand {

    /**
     * Administrator's displayable name
     */
    @NotBlank
    @Size(min = Administrator.NICKNAME_MIN_LENGTH, max = Administrator.NICKNAME_MAX_LENGTH)
    private String nickname = "";

}
