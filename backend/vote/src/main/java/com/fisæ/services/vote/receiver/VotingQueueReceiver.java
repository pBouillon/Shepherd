package com.fisæ.services.vote.receiver;

import com.fisæ.application.vote.VoteHandler;
import com.fisæ.services.vote.domain.message.VoteMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ listener
 */
@Log4j2
@Service
public class VotingQueueReceiver {

    /**
     * Handler for the user's vote
     */
    private final VoteHandler handler;

    /**
     * Initialize the listener
     *
     * @param voteHandler Handler for the user's vote
     */
    @Autowired
    public VotingQueueReceiver(VoteHandler voteHandler) {
        handler = voteHandler;
    }

    /**
     * Entrypoint for the messages received from the queue
     *
     * @param message A {@link VoteMessage} holding the user's vote
     */
    @RabbitListener(queues = "#{queue.name}")
    public void RabbitListener(VoteMessage message) {
        log.info("Vote received: {}", message);
        handler.handle(message);
    }

}
