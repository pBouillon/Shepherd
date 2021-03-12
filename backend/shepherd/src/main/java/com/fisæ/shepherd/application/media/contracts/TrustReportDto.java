package com.fisæ.shepherd.application.media.contracts;

import lombok.Data;

/**
 * TrustReport DTO, served by the API
 */
@Data
public class TrustReportDto {

    /**
     * Rating of how blindly the media can be trusted, in percentages
     */
    private float rate = 50;

}
