package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.application.commons.exception.EntityNotFoundException;
import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.command.DeleteMediaCommand;
import com.fisæ.shepherd.application.media.command.UpdateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.exception.CollidingMediaException;
import com.fisæ.shepherd.application.media.exception.MediaNotFoundException;

/**
 * Define a service able to handle the write-only operations (commands) performed on medias
 *
 * @see MediaService
 */
public interface MediaCommandService {

    /**
     * Create a new media
     *
     * @param command Payload containing the details to create the media
     *
     * @return The newly created media
     * @throws CollidingMediaException If a media already exists for the same name
     */
    MediaDto create(CreateMediaCommand command) throws CollidingMediaException;

    /**
     * Delete an existing media
     *
     * @param command Payload containing about the media to delete
     *
     * @throws EntityNotFoundException If there is not media that matches the provided id
     */
    void delete(DeleteMediaCommand command) throws MediaNotFoundException;

    /**
     * Update an existing media
     *
     * @param id Id of the media to update
     * @param command Payload containing the details to update the media
     *
     * @return The updated media
     * @throws EntityNotFoundException If there is not media that matches the provided id
     */
    MediaDto updateMedia(long id, UpdateMediaCommand command) throws MediaNotFoundException;

}
