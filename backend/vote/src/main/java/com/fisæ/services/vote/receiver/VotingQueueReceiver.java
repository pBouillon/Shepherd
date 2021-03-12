package com.fisæ.services.vote.receiver;

import com.fisæ.services.vote.domain.message.VoteMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class VotingQueueReceiver {

    @RabbitListener(queues = "#{queue.name}")
    public void RabbitListener(VoteMessage message) {
        log.info("Vote received: {}", message);
    }

}
