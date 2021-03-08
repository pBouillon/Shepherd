package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.command.DeleteMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.exception.MediaNotFoundException;
import com.fisæ.shepherd.domain.entity.Media;
import com.fisæ.shepherd.infrastructure.mapping.MediaMapper;
import com.fisæ.shepherd.infrastructure.persistence.repository.MediaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit test suite for the {@link MediaCommandService}
 */
@ExtendWith(MockitoExtension.class)
public class MediaCommandServiceTests {

    /**
     * Mocked MediaRepository, acting as the {@link Media} DAO
     */
    @Mock
    private MediaRepository repository;

    /**
     * Instance of the service to be tested, cleaned-up before each test
     */
    private MediaCommandService service;

    /**
     * Setup method, executed before each test
     */
    @BeforeEach
    public void setup() {
        MediaMapper mapper = Mappers.getMapper(MediaMapper.class);
        service = new MediaService(mapper, repository);
    }

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

        MediaDto created = service.create(command);

        assertAll("media creation",
                () -> verify(repository, times(1))
                        .save(any(Media.class)),
                () -> assertEquals(command.getName(), created.getName()));
    }

}
