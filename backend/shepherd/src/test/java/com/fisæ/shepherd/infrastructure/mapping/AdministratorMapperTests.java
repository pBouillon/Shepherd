package com.fisæ.shepherd.infrastructure.mapping;

import com.fisæ.shepherd.application.administrator.command.CreateAdministratorCommand;
import com.fisæ.shepherd.domain.entity.Administrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test suite for the {@link AdministratorMapper}
 */
public class AdministratorMapperTests {

    /**
     * Instance of the mapper to be tested, cleaned-up before each test
     */
    private AdministratorMapper mapper;

    /**
     * Setup method, executed before each test
     */
    @BeforeEach
    public void setup() {
        mapper = Mappers.getMapper(AdministratorMapper.class);
    }

    @Test
    public void givenACreateAdministratorCommand_WhenMappingIt_ThenAnAdministratorShouldKeepTheProvidedProperties() {
        CreateAdministratorCommand command = new CreateAdministratorCommand();
        command.setNickname("My administrator");

        Administrator administrator = mapper.toAdministrator(command);

        assertAll("administrator",
                () -> assertEquals(0L, administrator.getId()),
                () -> assertEquals(command.getNickname(), administrator.getNickname()));
    }

}
