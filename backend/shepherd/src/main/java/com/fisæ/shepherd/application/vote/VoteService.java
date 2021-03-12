package com.fisæ.shepherd.application.vote;

import com.fisæ.shepherd.application.commons.exception.EntityNotFoundException;
import com.fisæ.shepherd.application.media.exception.MediaNotFoundException;
import com.fisæ.shepherd.application.vote.command.UpdateVoteCommand;
import com.fisæ.shepherd.application.vote.messages.VoteMessage;
import com.fisæ.shepherd.domain.entity.Media;
import com.fisæ.shepherd.infrastructure.mapping.VoteMapper;
import com.fisæ.shepherd.infrastructure.persistence.repository.MediaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Define a service able to handle the operations performed on a media for its votes
 */
@Log4j2
@Service
public class VoteService implements VoteCommandService, VoteQueryService {

    /**
     * Mapper to convert the votes to their DTO
     */
    private final VoteMapper mapper;

    /**
     * DAO to access the persisted {@link Media} entities
     */
    private final MediaRepository repository;

    /**
     * Template used to send messages on the RabbitMQ queue
     */
    private final RabbitTemplate template;

    /**
     * Create the service
     *
     * @param mediaRepository DAO to access the persisted {@link Media} entities
     * @param rabbitTemplate Template used to send messages on the RabbitMQ queue
     * @param voteMapper Mapper to convert the votes to their DTO
     */
    @Autowired
    public VoteService(MediaRepository mediaRepository, RabbitTemplate rabbitTemplate, VoteMapper voteMapper) {
        mapper = voteMapper;
        repository = mediaRepository;
        template = rabbitTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateMediaVotes(long mediaId, UpdateVoteCommand command) throws MediaNotFoundException {
        if (!repository.existsById(mediaId))
        {
            throw new MediaNotFoundException(mediaId);
        }

        VoteMessage vote = mapper.toMessage(command);
        vote.setTargetedMediaId(mediaId);

        template.convertAndSend(vote);

        log.info("{} sent to the queue", vote);
    }

}
