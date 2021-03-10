package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.command.DeleteMediaCommand;
import com.fisæ.shepherd.application.media.command.UpdateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.exception.MediaNotFoundException;
import com.fisæ.shepherd.domain.entity.Media;
import com.fisæ.shepherd.infrastructure.persistence.repository.MediaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit test suite for the {@link MediaCommandService}
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MediaCommandServiceTests {

    /**
     * Mocked MediaRepository, acting as the {@link Media} DAO
     */
    @MockBean
    private MediaRepository repository;

    /**
     * Instance of the service to be tested, cleaned-up before each test
     */
    @Autowired
    private MediaCommandService service;

    @Test
    public void givenAnId_WhenDeletingTheAssociatedMedia_ThenItShouldBeDeleted() {
        Mockito.when(repository.findById(anyLong()))
                .thenReturn(Optional.of(new Media()));

        DeleteMediaCommand command = new DeleteMediaCommand();

        service.delete(command);

        verify(repository, times(1)).delete(any(Media.class));
    }

    @Test
    public void givenAnUnknownId_WhenDeletingTheAssociatedMedia_ThenAnExceptionShouldBeThrown() {
        Mockito.when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        DeleteMediaCommand command = new DeleteMediaCommand();

        assertThrows(
                MediaNotFoundException.class,
                () -> service.delete(command));
    }

    @Test
    public void givenAValidRequest_WhenCreatingAMedia_ThenItShouldBeSaved() {
        Mockito.when(repository.save(any(Media.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateMediaCommand command = new CreateMediaCommand();
        command.setName("A trustworthy source");
        command.setDescription("Hey that's a pretty long description !");
        command.setWebsite(Optional.of("https://fake.news"));

        MediaDto created = service.create(command);

        assertAll("media creation",
                () -> verify(repository, times(1))
                        .save(any(Media.class)),
                () -> assertEquals(command.getName(), created.getName()));
    }

    @Test
    public void givenAnExistingMedia_WhenUpdatingItFromAValidPayload_ThenItShouldBeUpdated() {
        Media media = new Media("An untrustworthy source");

        Mockito.when(repository.findById(anyLong()))
                .thenReturn(Optional.of(media));

        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setName("A trustworthy source");
        command.setDescription("Hey that's a pretty long description !");
        command.setWebsite(Optional.of("https://fake.news"));

        service.updateMedia(media.getId(), command);

        assertAll("updated media",
                () -> assertEquals(command.getName(), media.getName()),
                () -> assertEquals(command.getDescription(), media.getDescription()),
                () -> assertEquals(command.getWebsite().get(), media.getWebsite().get().toString()),
                () -> verify(repository, times(1)).save(any(Media.class)));
    }

    @Test
    public void givenANonExistingMedia_WhenUpdatingItFromAValidPayload_ThenAnExceptionShouldBeThrown() {
        Mockito.when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setName("A trustworthy source");
        command.setDescription("Hey that's a pretty long description !");
        command.setWebsite(Optional.of("https://fake.news"));

        assertThrows(
                MediaNotFoundException.class,
                () -> service.updateMedia(1L, command));
    }

}
