package com.fisæ.shepherd.api.controller.media;

import com.fisæ.shepherd.application.media.MediaCommandService;
import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * API controller used for the write-only operations on the media resource
 */
@RestController
@RequestMapping(
        path = "/api/medias",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = MediaController.CONTROLLER_TAG, tags = { MediaController.CONTROLLER_TAG })
public class MediaWriteController extends MediaController {

    /**
     * Service handling the read-only operations on the medias
     */
    private final MediaCommandService mediaService;

    /**
     * Instantiate the controller
     *
     * @param mediaCommandService Service handling the write-only operations on the medias
     */
    public MediaWriteController(MediaCommandService mediaCommandService) {
        mediaService = mediaCommandService;
    }

    /**
     * Endpoint for: POST /medias
     *
     * Create a new media
     *
     * @param command CQRS command  containing the details needed to create a new media
     *
     * @return A JSON payload containing the medias created
     */
    @PostMapping
    @Operation(summary = "Create a new media",
            description = """
                    The media's name must not be blank or empty.
                    It also should be between 3 and 32 chars.
                    """,
            responses = {
                    @ApiResponse(responseCode = "201", description = "Media successfully created"),
                    @ApiResponse(responseCode = "400", description = "Malformed body")
            })
    public ResponseEntity<MediaDto> post(
            @ApiParam(value = "Payload from which creating the media")
            @Valid @RequestBody CreateMediaCommand command) {
        MediaDto created = mediaService.create(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(created);
    }

}
