package com.fisæ.shepherd.application.vote.command;

import com.fisæ.shepherd.domain.entity.Media;
import lombok.Data;

/**
 * Command to send the user's voting intention toward a {@link Media}
 */
@Data
public class UpdateVoteCommand {

    /**
     * Vote formulated by the user
     */
    public boolean trustworthy;

}
