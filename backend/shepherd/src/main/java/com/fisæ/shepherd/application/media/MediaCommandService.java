package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;

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
     */
    MediaDto create(CreateMediaCommand command);

}
