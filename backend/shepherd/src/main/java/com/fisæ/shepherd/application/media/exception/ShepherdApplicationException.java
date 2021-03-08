package com.fisæ.shepherd.application.media.exception;

import java.util.Collections;
import java.util.Map;

/**
 * Base class for the exception that might be thrown in the application package
 */
public abstract class ShepherdApplicationException extends RuntimeException {

    /**
     * Create the exception
     *
     * @param message Reason of why the exception occurred
     */
    public ShepherdApplicationException(String message) {
        super(message);
    }

    /**
     * Get the formatted exception as a pair formatted as:
     * "reason": message
     *
     * @return The exception pair
     */
    public Map<String, String> getReason() {
        return Collections.singletonMap("reason", getMessage());
    }

}
