package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.exception.EntityNotFoundException;
import com.fisæ.shepherd.application.media.query.GetMediaQuery;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;
import com.fisæ.shepherd.domain.entity.Media;
import com.fisæ.shepherd.infrastructure.mapping.MediaMapper;
import com.fisæ.shepherd.infrastructure.persistence.repository.MediaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Define a service able to handle the operations performed on medias
 */
@Log4j2
@Service
public class MediaService implements MediaCommandService, MediaQueryService {

    /**
     * Mapper to convert Media entities to their DTO
     */
    private final MediaMapper mapper;

    /**
     * DAO to access the persisted {@link Media} entities
     */
    private final MediaRepository repository;

    /**
     * Create the service
     *
     * @param mediaMapper Mapper to convert Media entities to their DTO
     * @param mediaRepository DAO to access the persisted {@link Media} entities
     */
    @Autowired
    public MediaService(MediaMapper mediaMapper, MediaRepository mediaRepository) {
        mapper = mediaMapper;
        repository = mediaRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaDto create(CreateMediaCommand command) {
        log.info("Creating a new media from {}", command);

        Media entity = repository.save(mapper.toMedia(command));

        log.info("{} created", entity);

        return mapper.toDto(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaDto getMedia(GetMediaQuery query) throws EntityNotFoundException {
        // TODO: test + exception filter

        Media entity = repository.findById(query.getId())
                .orElseThrow(() -> new EntityNotFoundException(Media.class, query.getId().toString()));

        log.info("{} retrieved for the query {}", entity, query);

        return mapper.toDto(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<MediaDto> getMedias(GetMediasQuery query) {
        int pageId = query.getPageId();
        int itemsPerPage = query.getItemsPerPages();

        PageRequest request = PageRequest.of(pageId, itemsPerPage);
        log.info("{} medias on page {} requested", pageId, itemsPerPage);

        Page<Media> medias = repository.findAll(request);
        log.info("{} medias on {} pages retrieved", medias.getTotalElements(), medias.getTotalPages());

        return medias.map(mapper::toDto);
    }

}
