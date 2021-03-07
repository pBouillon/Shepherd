package com.fisæ.shepherd.api.controller.media;

import com.fisæ.shepherd.application.media.MediaCommandService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API controller used for the read-only operations on the media resource
 */
@RestController
@RequestMapping(
        path = "/api/medias",
        produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = MediaController.CONTROLLER_TAG)
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

}
