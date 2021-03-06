package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.api.contracts.MediaDto;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;
import com.fisæ.shepherd.infrastructure.mapping.MediaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Define a service able to handle the operations performed on medias
 */
@Service
public class MediaService implements MediaQueryService {

    /**
     * Mapper to convert Media entities to their DTO
     */
    private final MediaMapper mapper;

    /**
     * Create the service
     *
     * @param mediaMapper Mapper to convert Media entities to their DTO
     */
    @Autowired
    public MediaService(MediaMapper mediaMapper) {
        mapper = mediaMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MediaDto> getMedias(GetMediasQuery query) {
        // TODO: implementation, logging and mapping
        return null;
    }

}
