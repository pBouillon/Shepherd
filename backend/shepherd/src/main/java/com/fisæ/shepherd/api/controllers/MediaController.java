package com.fisæ.shepherd.api.controllers;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API controller used for the media resource
 */
@RestController
@RequestMapping(
        path = "/api/medias",
        produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Medias")
public class MediaController {
}
