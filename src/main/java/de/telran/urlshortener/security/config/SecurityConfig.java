package de.telran.urlshortener.security.config;

import de.telran.urlshortener.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Configuration class for setting up web application security.
 * <p>
 * This class configures security parameters such as disabling CSRF, session management,
 * authorization rules, and adding the JWT filter to the filter chain.
 * </p>
 *
 * @Configuration       - Indicates that this is a configuration class.
 * @EnableWebSecurity   - Enables Spring Security's web security support.
 * @RequiredArgsConstructor - Lombok annotation, generates a constructor for all final fields,
 *                           with parameter order same as field order.
 * @EnableMethodSecurity - Enables method security annotations like @PreAuthorize.
 *
 * @author A-R
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * The JWT filter bean for processing JWT tokens.
     */
    private final JwtFilter jwtFilter;

    public static final String[] USER_LIST = {
            "/auth/**",
            "/swagger-ui.html",
            "/swagger-ui/**"
    };
    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";


    /**
     * Configures the security filter chain.
     * <p>
     * This method sets up the security configurations such as CSRF disabling, session management policy,
     * authorization rules and adds the JWT filter after the UsernamePasswordAuthenticationFilter.
     * </p>
     *
     * @param http the HttpSecurity instance to configure.
     * @return the SecurityFilterChain instance.
     * @throws Exception if an error occurs during configuration.
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(USER_LIST).permitAll()
                                .requestMatchers(HttpMethod.GET,"/*").permitAll()
                                .requestMatchers(HttpMethod.POST,"/createUrl").hasRole(USER_ROLE)
                                .anyRequest().authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//       return http.csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
//                .authorizeHttpRequests(
//                        authz -> authz
//                                .requestMatchers("/auth/login", "/auth/token",
//                                        "/h2-console/**",
//                                        "/swagger-ui.html",
//                                        "/api/v1/auth/**", "/v3/api-docs/**", "/swagger-ui/**"
//                                         /*",/**"*/
//                                )
//                                .permitAll()
//                                .anyRequest().authenticated()
//                ).addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

