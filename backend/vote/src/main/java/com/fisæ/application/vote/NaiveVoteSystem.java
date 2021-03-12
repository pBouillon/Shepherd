package com.fisæ.application.vote;

import com.fisæ.services.vote.domain.entity.Media;
import com.fisæ.services.vote.domain.message.VoteMessage;
import com.fisæ.services.vote.infrastructure.persistence.repository.MediaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Define a naive system for handling the votes
 *
 * Simply increase or decrease the overall rating of a media according to the vote received
 */
@Log4j2
@Service
public class NaiveVoteSystem implements VoteHandler {

    /**
     * DAO to access the persisted {@link Media} entities
     */
    private final MediaRepository repository;

    @Autowired
    public NaiveVoteSystem(MediaRepository mediaRepository) {
        repository = mediaRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(VoteMessage vote) {
        Optional<Media> media = repository.findById(vote.targetedMediaId);

        if (media.isEmpty()) {
            log.warn("No media found for the provided id '{}'", vote.targetedMediaId);
            return;
        }

        Media entity = media.get();

        float currentRate = entity.getTrustReport().getRate();
        entity.getTrustReport().setRate(currentRate - 1);

        repository.save(entity);

        log.info("Vote acknowledged for {} (previous rate was: {})", entity, currentRate);
    }

}
