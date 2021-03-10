package com.fisæ.shepherd.application.administrator.contracts;

import lombok.Data;

/**
 * Administrator DTO, served by the API
 */
@Data
public class AdministratorDto {

    /**
     * Id of the administrator
     */
    private Long id = 0L;

    /**
     * Administrator's displayable name
     */
    private String nickname = "";

}
