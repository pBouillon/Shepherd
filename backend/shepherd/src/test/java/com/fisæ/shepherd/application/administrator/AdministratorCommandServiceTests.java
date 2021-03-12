package com.fisæ.shepherd.application.administrator;

import com.fisæ.shepherd.application.administrator.command.CreateAdministratorCommand;
import com.fisæ.shepherd.application.administrator.contracts.AdministratorDto;
import com.fisæ.shepherd.domain.entity.Administrator;
import com.fisæ.shepherd.infrastructure.persistence.repository.AdministratorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit test suite for the {@link AdministratorCommandService}
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdministratorCommandServiceTests {

    /**
     * Mocked AdministratorRepository, acting as the {@link Administrator} DAO
     */
    @MockBean
    private AdministratorRepository repository;

    /**
     * Instance of the service to be tested, cleaned-up before each test
     */
    @Autowired
    private AdministratorCommandService service;

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
