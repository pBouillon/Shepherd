package com.fisæ.shepherd.infrastructure.mapping;

import com.fisæ.shepherd.application.vote.command.UpdateVoteCommand;
import com.fisæ.shepherd.domain.entity.Vote;
import com.fisæ.shepherd.infrastructure.amqp.message.VoteMessage;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * Votes utility mappings
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VoteMapper {

    /**
     * Convert a vote command to a message
     *
     * @param command The CQRS command to convert
     *
     * @return The associated message
     */
    VoteMessage toMessage(UpdateVoteCommand command);

    /**
     * Convert a vote command to a persisted vote
     *
     * @param command The CQRS command to convert
     *
     * @return The associated entity
     */
    Vote toVote(UpdateVoteCommand command);

}
