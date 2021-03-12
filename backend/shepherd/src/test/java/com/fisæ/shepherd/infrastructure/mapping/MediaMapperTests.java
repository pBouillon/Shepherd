package com.fisæ.shepherd.infrastructure.mapping;

import com.fisæ.shepherd.application.media.command.CreateMediaCommand;
import com.fisæ.shepherd.application.media.command.UpdateMediaCommand;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.domain.entity.Media;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test suite for the {@link MediaMapper}
 */
@SpringBootTest
public class MediaMapperTests {

    /**
     * Instance of the mapper to be tested, cleaned-up before each test
     */
    @Autowired
    private MediaMapper mapper;

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
    public void givenACreateMediaCommand_WhenMappingIt_ThenAMediaShouldKeepTheProvidedProperties() {
        CreateMediaCommand command = new CreateMediaCommand();
        command.setDescription("This is a pretty good description");
        command.setName("A trustworthy source");

        Media media = mapper.toMedia(command);

        assertThat(media.getId()).isEqualTo(0L);
        assertThat(media.getDescription()).isEqualTo(media.getDescription());
        assertThat(media.getName()).isEqualTo(media.getName());
    }

    @Test
    public void givenAMedia_WhenMappingIt_ThenThePropertiesShouldRemainTheSame() {
        Media media = new Media("A trustworthy source");
        media.setId(1L);
        media.setDescription("This is a pretty good description");

        MediaDto dto = mapper.toDto(media);

        assertThat(media.getCreationDate()).isEqualTo(dto.getCreationDate());
        assertThat(media.getDescription()).isEqualTo(dto.getDescription());
        assertThat(media.getId()).isEqualTo(dto.getId());
        assertThat(media.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void givenASetOfMedias_WhenMappingThem_ThenThePropertiesShouldRemainTheSame() {
        List<Media> medias = generateMedias();

        List<MediaDto> dtos = mapper.toDtos(medias);

        for (int i = 0; i < medias.size(); ++i) {
            Media media = medias.get(i);
            MediaDto dto = dtos.get(i);

            assertThat(media.getCreationDate()).isEqualTo(dto.getCreationDate());
            assertThat(media.getDescription()).isEqualTo(dto.getDescription());
            assertThat(media.getId()).isEqualTo(dto.getId());
            assertThat(media.getName()).isEqualTo(dto.getName());
        }
    }

    @Test
    public void givenAMedia_WhenUpdatingItFromTheCommand_ThenThePropertiesShouldHaveBeenUpdated() {
        Media media = new Media("A trustworthy source");

        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setName("The new source's name");

        mapper.updateFromCommand(command, media);

        assertThat(media.getName()).isEqualTo(command.getName());
    }

}
