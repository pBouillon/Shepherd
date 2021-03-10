package com.fisæ.shepherd.api.controller.administrator;

import com.fisæ.shepherd.api.controller.ControllerTests;
import com.fisæ.shepherd.application.administrator.command.CreateAdministratorCommand;
import com.fisæ.shepherd.application.administrator.contracts.AdministratorDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test suite for {@link AdministratorWriteController}
 */
public class AdministratorWriteControllerTests extends ControllerTests {

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "a",
            "     ",
            "a very very long name, too long for the application to accept it"})
    public void givenAnInvalidCommand_WhenCallingPost_ThenABadRequestShouldBeReturned(String nickname)
            throws URISyntaxException {
        CreateAdministratorCommand command = new CreateAdministratorCommand();
        command.setNickname(nickname);

        URI administratorUri = getUriForRoute("/api/administrators");

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForEntity(administratorUri, command, AdministratorDto.class));
    }

    @Test
    public void givenAValidCommand_WhenCallingPost_ThenAMediaShouldHaveBeenCreatedAndReturned()
            throws URISyntaxException {
        CreateAdministratorCommand command = new CreateAdministratorCommand();
        command.setNickname("My administrator");

        URI administratorUri = getUriForRoute("/api/administrators");

        ResponseEntity<AdministratorDto> createdAdministratorResponse
                = restTemplate.postForEntity(administratorUri, command, AdministratorDto.class);

        AdministratorDto created = extractPayload(createdAdministratorResponse);

        assertAll("administrator created",
                () -> assertEquals(HttpStatus.CREATED, createdAdministratorResponse.getStatusCode()),
                () -> assertEquals(command.getNickname(), created.getNickname()));
    }

}
