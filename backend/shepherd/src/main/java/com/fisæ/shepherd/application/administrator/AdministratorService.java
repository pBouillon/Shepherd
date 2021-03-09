package com.fisæ.shepherd.application.administrator;

import com.fisæ.shepherd.application.administrator.command.CreateAdministratorCommand;
import com.fisæ.shepherd.application.administrator.contracts.AdministratorDto;
import com.fisæ.shepherd.infrastructure.mapping.AdministratorMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Define a service able to handle the operations performed on administrators
 */
@Log4j2
@Service
public class AdministratorService implements AdministratorCommandService {

    /**
     * Mapper to convert Administrator entities to their DTO
     */
    private final AdministratorMapper mapper;

    /**
     * Create the service
     *
     * @param administratorMapper Mapper to convert Administrator entities to their DTO
     */
    @Autowired
    public AdministratorService(AdministratorMapper administratorMapper) {
        mapper = administratorMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdministratorDto create(CreateAdministratorCommand command) {
        return null;
    }

}
