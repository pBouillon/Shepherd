package com.fisæ.shepherd.application.media.exception;

import com.fisæ.shepherd.application.commons.exception.EntityNotFoundException;
import com.fisæ.shepherd.domain.entity.Media;

/**
 * Custom exception to be thrown when looking for a Media that does not exists
 */
public class MediaNotFoundException extends EntityNotFoundException {

    /**
     * Create the exception
     *
     * @param id Id used to searched for the media
     */
    public MediaNotFoundException(Long id) {
        super(Media.class, id.toString());
    }

}
