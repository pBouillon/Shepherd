package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.exception.MediaNotFoundException;
import com.fisæ.shepherd.application.media.query.GetMediaQuery;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;
import com.fisæ.shepherd.domain.entity.Media;
import com.fisæ.shepherd.infrastructure.persistence.repository.MediaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Unit test suite for the {@link MediaQueryService}
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MediaQueryServiceTests {

    /**
     * Mocked MediaRepository, acting as the {@link Media} DAO
     */
    @MockBean
    private MediaRepository repository;

    /**
     * Instance of the service to be tested, cleaned-up before each test
     */
    @Autowired
    private MediaQueryService service;

    @Test
    public void givenAnIdThatDoesNotBelongToAnyMedia_WhenQueryingIt_ThenAnExceptionShouldBeThrown() {
        GetMediaQuery query = new GetMediaQuery();
        query.setId(1L);

        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                MediaNotFoundException.class,
                () -> service.getMedia(query));
    }

    @Test
    public void givenNoMedias_WhenQueryingAPage_ThenNoMediasShouldBeReturned() {
        Mockito.when(repository.findAllByOrderByName(any(PageRequest.class)))
                .thenReturn(Page.empty());

        GetMediasQuery query = new GetMediasQuery();

        Page<MediaDto> paginatedMedias = service.getMedias(query);

        assertTrue(paginatedMedias.isEmpty());
    }

    @Test
    public void givenTheIdOfAMedia_WhenQueryingIt_ThenTheMediaShouldBeReturned() {
        Media media = new Media();
        media.setId(1L);
        media.setName("A trustworthy source");
        media.setDescription("A detailed description");
        media.setWebsite(URI.create("https://news.org"));

        GetMediaQuery query = new GetMediaQuery();
        query.setId(1L);

        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(media));

        MediaDto returned = service.getMedia(query);

        assertAll("media",
                () -> assertEquals(media.getId(), returned.getId()),
                () -> assertEquals(media.getCreationDate(), returned.getCreationDate()),
                () -> assertEquals(media.getDescription(), returned.getDescription()),
                () -> assertEquals(media.getName(), returned.getName()),
                () -> assertEquals(media.getWebsite(), returned.getWebsite()));
    }

}
