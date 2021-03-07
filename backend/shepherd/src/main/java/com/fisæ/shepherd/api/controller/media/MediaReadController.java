package com.fisæ.shepherd.api.controller.media;

import com.fisæ.shepherd.application.media.MediaQueryService;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * API controller used for the read-only operations on the media resource
 */
@RestController
@RequestMapping(
        path = "/api/medias",
        produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = MediaController.CONTROLLER_TAG, tags = { MediaController.CONTROLLER_TAG })
public class MediaReadController extends MediaController {

    /**
     * Service handling the read-only operations on the medias
     */
    private final MediaQueryService mediaService;

    /**
     * Instantiate the controller
     *
     * @param mediaQueryService Service handling the read-only operations on the medias
     */
    @Autowired
    public MediaReadController(MediaQueryService mediaQueryService) {
        mediaService = mediaQueryService;
    }

    /**
     * Endpoint for: GET /medias
     *
     * Retrieve all medias
     *
     * @param query CQRS query containing any details that might be needed to filter the media
     *
     * @return A JSON payload containing a paginated result of the medias retrieved
     */
    @GetMapping
    @Operation(summary = "Retrieve the medias",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Medias successfully retrieved")
            })
    public ResponseEntity<Page<MediaDto>> get(
            @ModelAttribute @RequestParam(required = false) Optional<GetMediasQuery> query) {
        return ResponseEntity.ok()
                .body(mediaService.getMedias(query));

    }

}
