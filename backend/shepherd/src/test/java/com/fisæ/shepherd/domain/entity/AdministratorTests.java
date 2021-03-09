package com.fisæ.shepherd.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test suite for the {@link Administrator}
 */
public class AdministratorTests {

    @Test
    public void givenANickname_WhenCreatingANewAdministratorByItsName_ThenNoFieldsShouldBeNull() {
        String name = "Administrator";
        Administrator administrator = new Administrator(name);

        assertAll("administrator",
                () -> assertNotNull(administrator.getId()),
                () -> assertNotNull(administrator.getId()));
    }

    @Test
    public void givenNullAsTheNickname_WhenCreatingANewAdministratorByItsName_ThenAnExceptionShouldBeThrown() {
        assertThrows(
                NullPointerException.class,
                () -> new Administrator(null));
    }

    @Test
    public void whenCreatingANewAdministratorWithDefaultConstructor_ThenNoFieldsShouldBeNull() {
        Administrator media = new Administrator();

        assertAll("administrator",
            () -> assertNotNull(media.getId()),
            () -> assertNotNull(media.getId()));
    }

}
