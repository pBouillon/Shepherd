package com.fisæ.shepherd.api.controller.media;

import com.fisæ.shepherd.api.controller.ControllerTests;
import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.command.DeleteMediaCommand;
import com.fisæ.shepherd.application.media.command.UpdateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test suite for {@link MediaWriteController}
 */
public class MediaWriteControllerTests extends ControllerTests {

    /**
     * Create a command that is valid and pass the validators
     *
     * @return The command
     */
    private CreateMediaCommand getValidCreateMediaCommand() {
        CreateMediaCommand command = new CreateMediaCommand();

        command.setName("A completely valid name");
        command.setDescription("A valid description too");

        return command;
    }

    @ParameterizedTest
    @ValueSource(longs = { -1L, 0L })
    public void givenAnInvalidCommand_WhenCallingDelete_ThenABadRequestShouldBeReturned(long id)
            throws URISyntaxException {
        DeleteMediaCommand command = new DeleteMediaCommand();
        command.setId(id);

        String mediaUri = getUriForRoute("/api/medias/") + command.getId().toString();

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.delete(mediaUri));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "a",
            "     ",
            "a very very long name, too long for the application to accept it"})
    public void givenAnInvalidCommand_WhenCallingPost_ThenABadRequestShouldBeReturned(String name)
            throws URISyntaxException {
        CreateMediaCommand command = getValidCreateMediaCommand();
        command.setName(name);

        URI mediaUri = getUriForRoute("/api/medias");

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForEntity(mediaUri, command, MediaDto.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "a",
            "     ",
            """
            a very very very very very very very very very very very very very very very very very very very very very 
            very very very very very very very very very very very very very very very very very very very very very 
            very very very very very very very very very very very very very very very very very very long description,
            too long for the application to accept it
            """})
    public void givenAnInvalidDescriptionInTheCommand_WhenCallingUpdateOnAValidId_ThenABadRequestShouldBeReturned(
            String description)
            throws URISyntaxException {
        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setDescription(description);

        URI mediaUri = getUriForRoute("/api/medias/1");

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.put(mediaUri, command));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "a",
            "     ",
            "a very very long name, too long for the application to accept it"})
    public void givenAnInvalidNameInTheCommand_WhenCallingUpdateOnAValidId_ThenABadRequestShouldBeReturned(String name)
            throws URISyntaxException {
        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setName(name);

        URI mediaUri = getUriForRoute("/api/medias/1");

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.put(mediaUri, command));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "not a url",
            "not@a.url",
            "http://AnUnsecuredUrl",
            "sftp://NotTheRightProtocolUrl"})
    public void givenAnInvalidUrlInTheCommand_WhenCallingUpdateOnAValidId_ThenABadRequestShouldBeReturned(String rawUrl)
            throws URISyntaxException {
        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setWebsite(Optional.of(rawUrl));

        URI mediaUri = getUriForRoute("/api/medias/1");

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.put(mediaUri, command));
    }

    @Test
    public void givenAValidCommand_WhenCallingDeleteOnAnUnknownId_ThenABadRequestShouldBeReturned()
            throws URISyntaxException {
        DeleteMediaCommand command = new DeleteMediaCommand();
        command.setId(Long.MAX_VALUE);

        String mediaUri = getUriForRoute("/api/medias/") + command.getId().toString();

        Assertions.assertThrows(
                HttpClientErrorException.NotFound.class,
                () -> restTemplate.delete(mediaUri));
    }

    @Test
    public void givenAValidCommand_WhenCallingPost_ThenAMediaShouldHaveBeenCreatedAndReturned()
            throws URISyntaxException {
        CreateMediaCommand command = getValidCreateMediaCommand();

        URI mediaUri = getUriForRoute("/api/medias");

        ResponseEntity<MediaDto> createdMediaResponse = restTemplate.postForEntity(mediaUri, command, MediaDto.class);
        MediaDto created = extractPayload(createdMediaResponse);

        assertAll("media created",
                () -> assertEquals(HttpStatus.CREATED, createdMediaResponse.getStatusCode()),
                () -> assertEquals(command.getName(), created.getName()));
    }

    @ParameterizedTest
    @ValueSource(longs = { -1L, 0L })
    public void givenAValidCommand_WhenCallingUpdateOnAnInvalidId_ThenABadRequestShouldBeReturned(long id)
            throws URISyntaxException {
        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setName("A trustworthy source");

        String mediaUri = getUriForRoute("/api/medias/") + String.valueOf(id);

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.put(mediaUri, command));
    }

    @Test
    public void givenAValidCommand_WhenCallingUpdateOnAnUnknownId_ThenANotFoundShouldBeReturned()
            throws URISyntaxException {
        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setName("A trustworthy source");

        String mediaUri = getUriForRoute("/api/medias/") + String.valueOf(Long.MAX_VALUE);

        Assertions.assertThrows(
                HttpClientErrorException.NotFound.class,
                () -> restTemplate.delete(mediaUri));
    }

}
