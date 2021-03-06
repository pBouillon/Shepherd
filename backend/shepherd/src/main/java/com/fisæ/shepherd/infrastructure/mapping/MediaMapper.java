package com.fisæ.shepherd.infrastructure.mapping;

import com.fisæ.shepherd.api.contracts.Media;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

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
    Media toDto(com.fisæ.shepherd.domain.entities.Media media);

}
