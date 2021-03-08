package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.exception.EntityNotFoundException;
import com.fisæ.shepherd.application.media.query.GetMediaQuery;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;
import org.springframework.data.domain.Page;

/**
 * Define a service able to handle the read-only operations (queries) performed on medias
 *
 * @see MediaService
 */
public interface MediaQueryService {

    /**
     * Retrieve a specific media stored in the system
     *
     * @param query Payload containing the details about the search
     *
     * @return The media found
     * @throws EntityNotFoundException If no entity is found for the given query
     */
    MediaDto getMedia(GetMediaQuery query) throws EntityNotFoundException;

    /**
     * Retrieve the medias stored in the system
     *
     * @param query Payload containing any details that might be needed to filter the media
     *
     * @return A list of all the medias, filtered by the payload
     */
    Page<MediaDto> getMedias(GetMediasQuery query);

}
