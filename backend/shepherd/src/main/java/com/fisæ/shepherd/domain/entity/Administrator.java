package com.fisæ.shepherd.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represent an administrator
 *
 * An administrator is a user that can administrate other user's and platform's data
 */
@Entity
@Data
@NoArgsConstructor
public class Administrator {

    /**
     * Maximum length of an administrator's nickname
     */
    public static final int NICKNAME_MAX_LENGTH = 32;

    /**
     * Minimum length of an administrator's nickname
     */
    public static final int NICKNAME_MIN_LENGTH = 3;

    /**
     * Id of the administrator
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id = 0L;

    /**
     * Administrator's displayable name
     */
    @NonNull
    private String nickname = "";

    /**
     * Create a new administrator
     *
     * @param nickname Administrator's displayable name
     */
    public Administrator(@NonNull String nickname) {
        this.nickname = nickname;
    }

}
