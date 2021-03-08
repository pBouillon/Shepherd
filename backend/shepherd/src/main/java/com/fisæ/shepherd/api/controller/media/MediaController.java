package com.fisæ.shepherd.api.controller.media;

/**
 * Base class used for the read- and write-only controllers
 *
 * The subsequent separation is made to respect the CQRS principles
 * see: https://www.baeldung.com/cqrs-for-a-spring-rest-api
 */
public abstract class MediaController {

    /**
     * Tag used to identify the media controllers in the Swagger UI
     */
    protected static final String CONTROLLER_TAG = "media";

}
