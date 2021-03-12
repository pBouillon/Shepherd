package com.fisæ.shepherd.application.vote;

import com.fisæ.shepherd.application.commons.exception.EntityNotFoundException;
import com.fisæ.shepherd.application.media.exception.MediaNotFoundException;
import com.fisæ.shepherd.application.vote.command.UpdateVoteCommand;

/**
 * Define a service able to handle the write-only operations (queries) performed on votes
 *
 * @see VoteService
 */
public interface VoteCommandService {

    /**
     * Handle a new vote for a given media
     *
     * @param mediaId Id of the media for which this vote is for
     * @param command Payload containing the details to handle the new vote
     *
     * @throws EntityNotFoundException If there is not media that matches the provided id
     */
    void updateMediaVotes(long mediaId, UpdateVoteCommand command) throws MediaNotFoundException;

}
