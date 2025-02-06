package de.telran.urlshortener.security.dto;


import lombok.*;

import java.util.Set;

/**
 * Model class representing a user within the application.
 * <p>
 * This class encapsulates the user's login credentials, personal information, and roles.
 * </p>
 *
 * @Getter               - Lombok annotation to generate getters for all fields.
 * @Setter               - Lombok annotation to generate setters for all fields.
 * @AllArgsConstructor   - Lombok annotation to generate a constructor for all fields.
 * @NoArgsConstructor    - Lombok annotation to generate a no-args constructor.
 *
 * @author A-R
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    /**
     * The username (login) of the user.
     */
    private String login;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The roles assigned to the user.
     */
    private Set<Role> roles;

}

