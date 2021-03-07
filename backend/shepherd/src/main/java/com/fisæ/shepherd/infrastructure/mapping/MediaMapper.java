package com.fisæ.shepherd.infrastructure.mapping;

import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.domain.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
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

}
