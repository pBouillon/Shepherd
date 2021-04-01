package com.fisæ.shepherd.application.vote;

import com.fisæ.shepherd.application.media.exception.MediaNotFoundException;
import com.fisæ.shepherd.application.vote.command.UpdateVoteCommand;
import com.fisæ.shepherd.domain.entity.Media;
import com.fisæ.shepherd.domain.entity.Vote;
import com.fisæ.shepherd.infrastructure.amqp.message.VoteMessage;
import com.fisæ.shepherd.infrastructure.mapping.VoteMapper;
import com.fisæ.shepherd.infrastructure.persistence.repository.MediaRepository;
import com.fisæ.shepherd.infrastructure.persistence.repository.VoteRepository;
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
     * DAO to access the persisted {@link Media} entities
     */
    private final MediaRepository mediaRepository;

    /**
     * Template used to send messages on the RabbitMQ queue
     */
    private final RabbitTemplate template;

    /**
     * Mapper to convert the votes to their DTO
     */
    private final VoteMapper mapper;

    /**
     * DAO to access the persisted {@link Vote} entities
     */
    private final VoteRepository voteRepository;

    /**
     * Create the service
     *
     * @param mediaRepository DAO to access the persisted {@link Media} entities
     * @param voteRepository DAO to access the persisted {@link Vote} entities
     * @param rabbitTemplate Template used to send messages on the RabbitMQ queue
     * @param voteMapper Mapper to convert the votes to their DTO
     */
    @Autowired
    public VoteService(MediaRepository mediaRepository, VoteRepository voteRepository,
                       RabbitTemplate rabbitTemplate, VoteMapper voteMapper) {
        mapper = voteMapper;
        this.mediaRepository = mediaRepository;
        template = rabbitTemplate;
        this.voteRepository = voteRepository;
    }

    /**
     * Append the vote wrapped by the command to the other votes for this media
     *
     * @param command CQRS command wrapping the vote
     * @param media The targeted media
     */
    private void persistVoteFromCommand(UpdateVoteCommand command, Media media) {
        Vote vote = mapper.toVote(command);
        vote.setTrustReport(media.getTrustReport());

        voteRepository.save(vote);

        log.info("{} added to the votes", vote);
    }

    /**
     * Broadcast the vote intent to other Shepherd's services
     *
     * @param mediaId The id of the targeted media
     * @param command The original CQRS command wrapping the vote
     */
    private void sendVoteIntent(long mediaId, UpdateVoteCommand command) {
        VoteMessage message = mapper.toMessage(command);
        message.setTargetedMediaId(mediaId);

        template.convertAndSend(message);

        log.info("{} sent to the queue", message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateMediaVotes(long mediaId, UpdateVoteCommand command) throws MediaNotFoundException {
        Media media = mediaRepository
                .findById(mediaId)
                .orElseThrow(() -> new MediaNotFoundException(mediaId));

        persistVoteFromCommand(command, media);

        sendVoteIntent(media.getId(), command);
    }

}
