package com.fisæ.shepherd.application.administrator.command;

import com.fisæ.shepherd.application.administrator.AdministratorCommandService;
import com.fisæ.shepherd.application.administrator.AdministratorService;
import com.fisæ.shepherd.application.administrator.contracts.AdministratorDto;
import com.fisæ.shepherd.domain.entity.Administrator;
import com.fisæ.shepherd.infrastructure.mapping.AdministratorMapper;
import com.fisæ.shepherd.infrastructure.persistence.repository.AdministratorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit test suite for the {@link AdministratorCommandService}
 */
@ExtendWith(MockitoExtension.class)
public class AdministratorCommandServiceTests {

    /**
     * Mocked AdministratorRepository, acting as the {@link Administrator} DAO
     */
    @Mock
    private AdministratorRepository repository;

    /**
     * Instance of the service to be tested, cleaned-up before each test
     */
    private AdministratorCommandService service;

    /**
     * Setup method, executed before each test
     */
    @BeforeEach
    public void setup() {
        AdministratorMapper mapper = Mappers.getMapper(AdministratorMapper.class);
        service = new AdministratorService(mapper, repository);
    }

    @Test
    public void givenAValidRequest_WhenCreatingAnAdministrator_ThenItShouldBeSaved() {
        Mockito.when(repository.save(any(Administrator.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateAdministratorCommand command = new CreateAdministratorCommand();
        command.setNickname("My administrator");

        AdministratorDto created = service.create(command);

        assertAll("administrator creation",
                () -> verify(repository, times(1))
                        .save(any(Administrator.class)),
                () -> assertEquals(command.getNickname(), created.getNickname()));
    }

}
