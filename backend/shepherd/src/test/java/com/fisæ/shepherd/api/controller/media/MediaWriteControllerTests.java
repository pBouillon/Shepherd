package com.fisæ.shepherd.api.controller.media;

import com.fisæ.shepherd.api.controller.ControllerTests;
import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.command.DeleteMediaCommand;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test suite for {@link MediaWriteController}
 */
public class MediaWriteControllerTests extends ControllerTests {

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
        CreateMediaCommand command = new CreateMediaCommand();
        command.setName(name);

        URI mediaUri = getUriForRoute("/api/medias");

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForEntity(mediaUri, command, MediaDto.class));
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
        CreateMediaCommand command = new CreateMediaCommand();
        command.setName("A trustworthy source");

        URI mediaUri = getUriForRoute("/api/medias");

        ResponseEntity<MediaDto> createdMediaResponse = restTemplate.postForEntity(mediaUri, command, MediaDto.class);
        MediaDto created = extractPayload(createdMediaResponse);

        assertAll("media created",
                () -> assertEquals(HttpStatus.CREATED, createdMediaResponse.getStatusCode()),
                () -> assertEquals(command.getName(), created.getName()));
    }

}
