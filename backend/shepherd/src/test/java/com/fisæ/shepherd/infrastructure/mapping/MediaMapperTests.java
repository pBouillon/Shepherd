package com.fisæ.shepherd.infrastructure.mapping;

import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.domain.entity.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test suite for the {@link MediaMapper}
 */
public class MediaMapperTests {

    /**
     * Instance of the mapper to be tested, cleaned-up before each test
     */
    private MediaMapper mapper;

    /**
     * Setup method, executed before each test
     */
    @BeforeEach
    public void setup() {
        mapper = Mappers.getMapper(MediaMapper.class);
    }

    /**
     * Utility method to generate a collection of medias
     *
     * @return A collection of initialized medias
     */
    private static List<Media> generateMedias() {
        List<Media> medias = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            Media media = new Media(String.valueOf(i));
            media.setId((long) i);

            medias.add(media);
        }

        return medias;
    }

    @Test
    public void givenAMedia_WhenMappingIt_ThenThePropertiesShouldRemainTheSame() {
        Media media = new Media("A trustworthy source");
        media.setId(1L);

        MediaDto dto = mapper.toDto(media);

        assertAll("media",
                () -> assertEquals(media.getCreationDate(), dto.getCreationDate()),
                () -> assertEquals(media.getId(), dto.getId()),
                () -> assertEquals(media.getCreationDate(), dto.getCreationDate()));
    }

    @Test
    public void givenASetOfMedias_WhenMappingThem_ThenThePropertiesShouldRemainTheSame() {
        List<Media> medias = generateMedias();

        List<MediaDto> dtos = mapper.toDtos(medias);

        for (int i = 0; i < medias.size(); ++i) {
            Media media = medias.get(i);
            MediaDto dto = dtos.get(i);

            assertAll("media",
                    () -> assertEquals(media.getCreationDate(), dto.getCreationDate()),
                    () -> assertEquals(media.getId(), dto.getId()),
                    () -> assertEquals(media.getCreationDate(), dto.getCreationDate()));
        }
    }

}
