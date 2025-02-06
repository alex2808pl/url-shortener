package de.telran.urlshortener.security.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Enumeration for representing user roles within the application.
 * <p>
 * This enum implements the {@link GrantedAuthority} interface from Spring Security,
 * which is a standard interface for representing authorities, in this case, roles.
 * </p>
 *
 * @RequiredArgsConstructor - Lombok annotation to generate a constructor for all final fields,
 *                           with parameter order same as field order.
 *
 * @author A-R
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    /**
     * Role representing administrative users.
     */
    ADMIN("ADMIN"),

    EDITOR("EDITOR"), // моя роль
    /**
     * Role representing standard users.
     */
    USER("USER");



    /**
     * The value of the role, which will be returned by the {@link #getAuthority()} method.
     */
    private final String value;

    /**
     * Returns the authority (role) value.
     *
     * @return the value of the role.
     */
    @Override
    public String getAuthority() {
        return value;
    }

}


