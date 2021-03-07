package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

/**
 * Unit test suite for the {@link MediaQueryService}
 */
@ExtendWith(MockitoExtension.class)
public class MediaQueryServiceTests {

    /**
     * Mocked MediaRepository, acting as the {@link Media} DAO
     */
    @Mock
    private MediaRepository repository;

    /**
     * Instance of the service to be tested, cleaned-up before each test
     */
    private MediaQueryService service;

    /**
     * Setup method, executed before each test
     */
    @BeforeEach
    public void setup() {
        MediaMapper mapper = Mappers.getMapper(MediaMapper.class);
        service = new MediaService(mapper, repository);
    }


    /**
     * Create a page of the provided list according to the provided page request
     *
     * @param medias Original list to be paginated
     * @param pageRequest Page parameters
     *
     * @return The page wrapping the sliced list
     */
    public static Page<Media> getPageFromList(List<Media> medias, PageRequest pageRequest) {
        int start = Math.toIntExact(pageRequest.getOffset());
        int end = Math.min((start + pageRequest.getPageSize()), medias.size());

        List<Media> slice = medias.subList(start, end);

        return new PageImpl<>(slice, pageRequest, medias.size());
    }

    @Test
    public void givenNoMedias_WhenQueryingAPage_ThenNoMediasShouldBeReturned() {
        Mockito.when(repository.findAll(any(PageRequest.class)))
                .thenReturn(Page.empty());

        GetMediasQuery query = new GetMediasQuery();

        Page<MediaDto> paginatedMedias = service.getMedias(Optional.of(query));

        assertTrue(paginatedMedias.isEmpty());
    }

    @Test
    public void givenSeveralMedias_WhenQueryingAPage_ThenTheResultShouldBePaginated() {
        List<Media> medias = List.of(
                new Media("CNN"),
                new Media("Fox News"));

        Mockito.when(repository.findAll(any(PageRequest.class)))
                .thenAnswer(invocation -> getPageFromList(medias, invocation.getArgument(0)));

        GetMediasQuery query = new GetMediasQuery();
        query.setPageId(0);
        query.setItemsPerPages(1);

        Page<MediaDto> paginatedMedias = service.getMedias(Optional.of(query));

        assertEquals(query.getItemsPerPages(), paginatedMedias.getSize());
        assertEquals(medias.size(), paginatedMedias.getTotalElements());

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        MediaDto returned = paginatedMedias.get()
                .findFirst()
                .get();

        assertEquals(returned.getName(), medias.get(0).getName());
    }

}
