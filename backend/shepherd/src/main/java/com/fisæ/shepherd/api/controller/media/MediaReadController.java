package com.fisæ.shepherd.api.controller.media;

import com.fisæ.shepherd.application.media.MediaQueryService;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.query.GetMediaQuery;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;
import com.fisæ.shepherd.domain.entity.Media;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.Optional;

import static com.fisæ.shepherd.application.media.query.GetMediasQuery.*;

/**
 * API controller used for the read-only operations on the media resource
 */
@RestController
@RequestMapping(
        path = "/api/medias",
        produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = MediaController.CONTROLLER_TAG, tags = { MediaController.CONTROLLER_TAG })
@Validated
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
     * Endpoint for: GET /medias/{id}
     *
     * Retrieve a media by its id
     *
     * @param id Id of the media to retrieve
     *
     * @return A JSON payload containing the media retrieved
     */
    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Retrieve a specific media",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Media successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid id"),
                    @ApiResponse(responseCode = "404", description = "No media found for the provided id")
            })
    public ResponseEntity<MediaDto> getMedia(
            @ApiParam(value = "Id of the media to retrieve")
            @PathVariable
            @Min(Media.ID_MIN_VALUE) long id) {
        GetMediaQuery query = new GetMediaQuery();
        query.setId(id);

        return ResponseEntity.ok()
                .body(mediaService.getMedia(query));
    }

    /**
     * Endpoint for: GET /medias?pageId=_&itemsPerPages=_
     *
     * Retrieve a paginated view of all medias
     *
     * @param pageId Offset of the page to retrieve
     * @param itemsPerPages Number of items to display per page
     *
     * @return A JSON payload containing a paginated result of the medias retrieved
     */
    @GetMapping
    @Operation(
            summary = "Retrieve the medias",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Medias successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid pagination options")
            })
    public ResponseEntity<Page<MediaDto>> getMedias(
            @ApiParam(value = "Number of items to display per page")
            @RequestParam(defaultValue = "10")
            @Range(min = ITEMS_PER_PAGES_MIN_VALUE, max = ITEMS_PER_PAGES_MAX_VALUE)
            int itemsPerPages,
            @ApiParam(value = "Name of the media to filter the results on")
            @RequestParam(required = false)
            Optional<String> name,
            @ApiParam(value = "Offset of the page to retrieve")
            @RequestParam(defaultValue = "0")
            @Min(PAGE_ID_MIN_VALUE)
            int pageId,
            @ApiParam(value = "Website of the media to filter the results on")
            @RequestParam(required = false)
            Optional<@URL(protocol = "https") String> website) {
        GetMediasQuery query = new GetMediasQuery();
        query.setItemsPerPages(itemsPerPages);
        query.setPageId(pageId);
        query.setName(name);
        query.setWebsite(website);

        return ResponseEntity.ok()
                .body(mediaService.getMedias(query));
    }

}
