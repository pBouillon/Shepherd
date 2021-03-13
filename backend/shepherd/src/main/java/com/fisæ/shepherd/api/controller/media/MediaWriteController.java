package com.fisæ.shepherd.api.controller.media;

import com.fisæ.shepherd.application.media.MediaCommandService;
import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.command.DeleteMediaCommand;
import com.fisæ.shepherd.application.media.command.UpdateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.vote.VoteCommandService;
import com.fisæ.shepherd.application.vote.command.UpdateVoteCommand;
import com.fisæ.shepherd.domain.entity.Media;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;

/**
 * API controller used for the write-only operations on the media resource
 */
@RestController
@RequestMapping(
        path = "/api/medias",
        produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = MediaController.CONTROLLER_TAG, tags = { MediaController.CONTROLLER_TAG })
@Validated
public class MediaWriteController extends MediaController {

    /**
     * Service handling the write-only operations on the medias
     */
    private final MediaCommandService mediaService;

    /**
     * Service handling the write-only operations regarding the votes
     */
    private final VoteCommandService voteService;

    /**
     * Instantiate the controller
     *
     * @param mediaCommandService Service handling the write-only operations on the medias
     * @param voteCommandService Service handling the write-only operations regarding the votes
     */
    public MediaWriteController(MediaCommandService mediaCommandService, VoteCommandService  voteCommandService) {
        mediaService = mediaCommandService;
        voteService = voteCommandService;
    }

    /**
     * Endpoint for: DELETE /medias/:id
     *
     * Delete a media by its id
     *
     * @param id Id of the media to delete
     *
     * @return No Content on success
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a media",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Media successfully deleted"),
                    @ApiResponse(responseCode = "400", description = "Invalid id"),
                    @ApiResponse(responseCode = "404", description = "Media not found")
            })
    public ResponseEntity<?> deleteMedia(
            @ApiParam(value = "Id of the media to delete")
            @PathVariable @Min(Media.ID_MIN_VALUE) long id) {
        DeleteMediaCommand command = new DeleteMediaCommand();
        command.setId(id);

        mediaService.delete(command);

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint for: POST /medias
     *
     * Create a new media
     *
     * @param command CQRS command containing the details needed to create a new media
     *
     * @return A JSON payload containing the media created
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
    public ResponseEntity<MediaDto> postMedia(
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

    /**
     * Endpoint for: PUT /medias/:id
     *
     * Update a media by its id
     *
     * @param id Id of the media to update
     * @param command CQRS command containing the details needed to update the media
     *
     * @return The JSON payload containing the updated media
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing media",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Media successfully updated"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters"),
                    @ApiResponse(responseCode = "404", description = "Media not found")
    })
    public ResponseEntity<MediaDto> putMedia(
            @ApiParam("Id of the media to update")
            @PathVariable @Min(Media.ID_MIN_VALUE) long id,
            @ApiParam("Payload from which the media will be updated")
            @Valid @RequestBody UpdateMediaCommand command) {
        return ResponseEntity.ok(mediaService.updateMedia(id, command));
    }

    /**
     * Endpoint for: PUT /medias/:id/votes
     *
     * Update the media's score by modifying its votes
     *
     * @param id Id of the media to update
     * @param command CQRS command containing the details needed to handle the vote
     *
     * @return Accepted to when the vote is acknowledged and differed
     */
    @PutMapping("/{id}/votes")
    @Operation(
            summary = "Update the media's score by modifying its votes",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Vote successfully received"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters"),
                    @ApiResponse(responseCode = "404", description = "Media not found")
            })
    public ResponseEntity<?> putMediaVote(
            @ApiParam("Id of the media to update")
            @PathVariable @Min(Media.ID_MIN_VALUE) long id,
            @ApiParam("Payload from which the vote for the media will be handled")
            @Valid @RequestBody UpdateVoteCommand command) {
        voteService.updateMediaVotes(id, command);
        return ResponseEntity.accepted().build();
    }

}
