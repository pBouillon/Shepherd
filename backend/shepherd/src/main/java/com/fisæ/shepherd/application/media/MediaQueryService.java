package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.api.contracts.MediaDto;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;

import java.util.List;

/**
 * Define a service able to handle the read-only operations (queries) performed on medias
 *
 * @see MediaService
 */
public interface MediaQueryService {

    /**
     * Retrieve the medias stored in the system
     *
     * @param query Payload containing any details that might be needed to filter the media
     *
     * @return A list of all the medias, filtered by the payload
     */
    List<MediaDto> getMedias(GetMediasQuery query);

}
