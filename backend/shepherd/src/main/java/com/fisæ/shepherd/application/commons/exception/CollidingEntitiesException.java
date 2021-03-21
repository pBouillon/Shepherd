package com.fisæ.shepherd.application.commons.exception;

/**
 * Exception thrown whenever an operation would collide with another entity
 */
public class CollidingEntitiesException extends ShepherdApplicationException {

    /**
     * Create the exception
     *
     * @param message Exception message detailing the reason
     */
    public CollidingEntitiesException(String message) {
        super(message);
    }

}