package com.fisæ.shepherd.infrastructure.mapping;

import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.command.UpdateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.domain.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * {@link Media} utility mappings
 */
@Mapper(componentModel = "spring",
        // FIXME: uses = UriMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MediaMapper {

    /**
     * Convert a media entity to its DTO
     *
     * @param media The entity to convert
     *
     * @return The associated DTO
     */
    MediaDto toDto(Media media);

    /**
     * Convert a collection of media entities to the associated list of DTO
     *
     * @param medias Entities to convert
     *
     * @return The associated DTOs
     */
    List<MediaDto> toDtos(List<Media> medias);

    /**
     * Convert a {@link CreateMediaCommand} to a {@link Media}
     *
     * @param command Command to convert
     *
     * @return The associated media
     */
    Media toMedia(CreateMediaCommand command);

    /**
     * Map an optional URI to an optional String
     *
     * @param value Optional URI to map
     *
     * @return The resulting optional String
     */
    default Optional<String> toOptionalString(Optional<URI> value) {
        return value.isPresent()
                ? Optional.of(value.get().toString())
                : Optional.empty();
    }

    /**
     * Map an optional String to an optional URI
     *
     * @param value Optional String to map
     *
     * @return The resulting optional URI
     */
    default Optional<URI> toOptionalUri(Optional<String> value) {
        return value.isPresent()
                ? Optional.of(URI.create(value.get().trim()))
                : Optional.empty();
    }

    /**
     * Update the content of a {@link Media} by the values held by the {@link UpdateMediaCommand}
     *
     * @param command Payload holding the new values used to replace the existing ones
     * @param media Media to be updated
     */
    void updateFromCommand(UpdateMediaCommand command, @MappingTarget Media media);

}
