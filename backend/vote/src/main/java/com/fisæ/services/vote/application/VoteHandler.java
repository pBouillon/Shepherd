package com.fisæ.services.vote.application;

import com.fisæ.services.vote.domain.message.VoteMessage;

/**
 * Define a class able to handle the user's vote
 */
public interface VoteHandler {

    /**
     * Handle the user's vote to alter the media's score
     *
     * @param vote User's vote
     */
    void handle(VoteMessage vote);

}
