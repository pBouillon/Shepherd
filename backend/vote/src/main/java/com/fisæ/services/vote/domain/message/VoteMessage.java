package com.fisæ.services.vote.domain.message;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty
    public long targetedMediaId = 0;

}
