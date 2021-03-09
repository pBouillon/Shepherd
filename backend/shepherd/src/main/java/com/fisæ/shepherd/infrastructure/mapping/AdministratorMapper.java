package com.fisæ.shepherd.infrastructure.mapping;

import com.fisæ.shepherd.application.administrator.command.CreateAdministratorCommand;
import com.fisæ.shepherd.domain.entity.Administrator;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * {@link Administrator} utility mappings
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdministratorMapper {

    /**
     * Convert a {@link CreateAdministratorCommand} to an {@link Administrator}
     *
     * @param command Command to convert
     *
     * @return The associated administrator
     */
    Administrator toAdministrator(CreateAdministratorCommand command);

}
