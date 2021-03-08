package com.fisæ.shepherd.application.commons.exception;

/**
 * Exception thrown when attempting to retrieve a non-existing entity
 */
public class EntityNotFoundException extends ShepherdApplicationException {

    /**
     * Create the exception
     *
     * @param type Type of the object that has not been found
     * @param key Key used to search it
     */
    public EntityNotFoundException(Class<?> type, String key) {
        super("Unable to retrieve an entity of type " + type.getSimpleName() + " using the key '" + key + "'");
    }

}
