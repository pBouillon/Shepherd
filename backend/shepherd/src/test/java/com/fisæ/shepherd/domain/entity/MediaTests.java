package com.fisæ.shepherd.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test suite for the {@link Media}
 */
public class MediaTests {

    @Test
    public void givenAName_WhenCreatingANewMediaByItsName_ThenNoFieldsShouldBeNull() {
        String name = "A trustworthy source";
        Media media = new Media(name);

        assertAll("media",
                () -> assertNotNull(media.getCreationDate()),
                () -> assertNotNull(media.getDescription()),
                () -> assertNotNull(media.getId()),
                () -> assertEquals(name, media.getName()),
                () -> assertTrue(media.getWebsite().isEmpty()));
    }

    @Test
    public void givenNullAsTheName_WhenCreatingANewMediaByItsName_ThenAnExceptionShouldBeThrown() {
        assertThrows(
                NullPointerException.class,
                () -> new Media(null));
    }

    @Test
    public void whenCreatingANewMediaWithDefaultConstructor_ThenNoFieldsShouldBeNull() {
        Media media = new Media();

        assertAll("media",
            () -> assertNotNull(media.getCreationDate()),
            () -> assertNotNull(media.getDescription()),
            () -> assertNotNull(media.getId()),
            () -> assertNotNull(media.getName()),
            () -> assertTrue(media.getWebsite().isEmpty()));
    }

}
