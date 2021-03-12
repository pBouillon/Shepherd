package com.fisæ.shepherd.infrastructure.mapping;

import com.fisæ.shepherd.application.administrator.command.CreateAdministratorCommand;
import com.fisæ.shepherd.application.administrator.contracts.AdministratorDto;
import com.fisæ.shepherd.domain.entity.Administrator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test suite for the {@link AdministratorMapper}
 */
@SpringBootTest
public class AdministratorMapperTests {

    /**
     * Instance of the mapper to be tested, cleaned-up before each test
     */
    @Autowired
    private AdministratorMapper mapper;

    @Test
    public void givenACreateAdministratorCommand_WhenMappingIt_ThenAnAdministratorShouldKeepTheProvidedProperties() {
        CreateAdministratorCommand command = new CreateAdministratorCommand();
        command.setNickname("My administrator");

        Administrator administrator = mapper.toAdministrator(command);

        assertAll("administrator",
                () -> assertEquals(0L, administrator.getId()),
                () -> assertEquals(command.getNickname(), administrator.getNickname()));
    }

    @Test
    public void givenAnAdministrator_WhenMappingIt_ThenThePropertiesShouldRemainTheSame() {
        Administrator administrator = new Administrator("My administrator");
        administrator.setId(1L);

        AdministratorDto dto = mapper.toDto(administrator);

        assertAll("administrator DTO",
                () -> assertEquals(administrator.getId(), dto.getId()),
                () -> assertEquals(administrator.getNickname(), dto.getNickname()));
    }

}
