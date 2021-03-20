package com.fisæ.shepherd.application.media.exception;

import com.fisæ.shepherd.application.commons.exception.CollidingEntitiesException;

/**
 * Exception thrown whenever an operation would collide with another media
 */
public class CollidingMediaException extends CollidingEntitiesException {

    /**
     * Create the exception
     *
     * @param message Exception message detailing the reason
     */
    public CollidingMediaException(String message) {
        super(message);
    }

}
