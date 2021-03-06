package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.api.contracts.Media;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Define a service able to handle the operations performed on medias
 */
@Service
public class MediaService implements MediaQueryService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Media> getMedias(GetMediasQuery query) {
        // TODO: implementation, logging and mapping
        return null;
    }

}
