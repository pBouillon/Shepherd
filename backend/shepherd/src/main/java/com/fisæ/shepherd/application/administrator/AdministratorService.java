package com.fisæ.shepherd.application.administrator;

import com.fisæ.shepherd.application.administrator.command.CreateAdministratorCommand;
import com.fisæ.shepherd.application.administrator.contracts.AdministratorDto;
import com.fisæ.shepherd.domain.entity.Administrator;
import com.fisæ.shepherd.infrastructure.mapping.AdministratorMapper;
import com.fisæ.shepherd.infrastructure.persistence.repository.AdministratorRepository;
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
     * DAO to access the persisted {@link Administrator} entities
     */
    private final AdministratorRepository repository;

    /**
     * Create the service
     *
     * @param administratorMapper Mapper to convert Administrator entities to their DTO
     * @param administratorRepository DAO to access the persisted {@link Administrator} entities
     */
    @Autowired
    public AdministratorService(
            AdministratorMapper administratorMapper, AdministratorRepository administratorRepository) {
        mapper = administratorMapper;
        repository = administratorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdministratorDto create(CreateAdministratorCommand command) {
        log.info("Creating a new administrator from {}", command);

        Administrator entity = repository.save(mapper.toAdministrator(command));

        log.info("{} created", entity);

        return mapper.toDto(entity);
    }

}
