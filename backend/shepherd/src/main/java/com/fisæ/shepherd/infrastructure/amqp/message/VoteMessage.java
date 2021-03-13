package com.fisæ.shepherd.infrastructure.amqp.message;

import lombok.Data;

import java.io.Serializable;

/**
 * POCO representing a vote to be handled by the voting service
 */
@Data
public class VoteMessage implements Serializable {

    /**
     * Id of the media for which this vote is
     */
    public long targetedMediaId = 0;

    /**
     * Vote formulated by the user
     */
    public boolean trustworthy;

}
