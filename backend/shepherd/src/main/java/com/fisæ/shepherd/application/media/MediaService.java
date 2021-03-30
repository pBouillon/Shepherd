package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.command.DeleteMediaCommand;
import com.fisæ.shepherd.application.media.command.UpdateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.exception.CollidingMediaException;
import com.fisæ.shepherd.application.media.exception.MediaNotFoundException;
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

import java.net.URI;
import java.util.Optional;

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
    public MediaDto create(CreateMediaCommand command) throws CollidingMediaException {
        if (repository.existsByNameIgnoreCase(command.getName()))
        {
            log.warn("Unable to create a media from {}: another media already exists with the provided name", command);
            throw new CollidingMediaException("A media already exists with the name " + command.getName());
        }

        log.info("Creating a new media from {}", command);

        Media entity = repository.save(mapper.toMedia(command));

        log.info("{} created", entity);

        return mapper.toDto(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(DeleteMediaCommand command) throws MediaNotFoundException {
        Media entity = repository.findById(command.getId())
                .orElseThrow(() -> new MediaNotFoundException(command.getId()));

        repository.delete(entity);

        log.info("{} deleted", entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaDto getMedia(GetMediaQuery query) throws MediaNotFoundException {
        Media entity = repository.findById(query.getId())
                .orElseThrow(() -> new MediaNotFoundException(query.getId()));

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

        Optional<String> name = query.getName();
        Optional<String> website = query.getWebsite();

        Page<Media> medias;

        if (name.isEmpty() && website.isEmpty())
        {
            medias = repository.findAll(request);
        }
        else if (name.isPresent() && website.isEmpty())
        {
            medias = repository.findAllByNameContainingIgnoreCaseOrderByNameAsc(name.get(), request);
        }
        else if (name.isEmpty())
        {
            medias = repository.findAllByWebsiteOrderByNameAsc(URI.create(website.get()), request);
        }
        else
        {
            medias = repository.findAllByNameContainingIgnoreCaseAndWebsiteOrderByNameAsc(
                    name.get(), URI.create(website.get()), request);
        }

        log.info("{} medias on {} pages retrieved", medias.getTotalElements(), medias.getTotalPages());

        return medias.map(mapper::toDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaDto updateMedia(long id, UpdateMediaCommand command) throws MediaNotFoundException {
        Media entity = repository.findById(id)
                .orElseThrow(() -> new MediaNotFoundException(id));

        log.info("Updating {} from {}", entity, command);

        mapper.updateFromCommand(command, entity);
        repository.save(entity);

        return mapper.toDto(entity);
    }

}
