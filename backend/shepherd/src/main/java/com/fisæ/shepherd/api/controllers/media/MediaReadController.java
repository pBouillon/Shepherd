package com.fisæ.shepherd.api.controllers.media;

import com.fisæ.shepherd.api.contracts.Media;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * API controller used for the read-only operations on the media resource
 */
@RestController
@RequestMapping(
        path = "/api/medias",
        produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = MediaController.CONTROLLER_TAG)
public class MediaReadController extends MediaController {

    /**
     * Endpoint for: GET /medias
     *
     * Retrieve all medias
     *
     * @return A JSON payload containing all the medias
     */
    @GetMapping
    @Operation(summary = "Retrieve all medias",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Medias successfully retrieved")
            })
    public ResponseEntity<List<Media>> get() {
        return ResponseEntity.ok()
                .body(new ArrayList<>());
    }

}
