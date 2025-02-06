package de.telran.urlshortener.security.service;

import de.telran.urlshortener.security.dto.Role;
import de.telran.urlshortener.security.dto.UserDto;
import de.telran.urlshortener.security.entity.UserEntity;
import de.telran.urlshortener.security.exception.ResponseException;
import de.telran.urlshortener.security.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for handling user-related operations.
 * <p>
 * This service provides methods for fetching user data based on login credentials.
 * It initializes a list of users for demonstration purposes.
 * </p>
 *
 * @Service - Indicates that an annotated class is a service component.
 *
 * @author A-R
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserService {

    /**
     * A list of users initialized for demonstration purposes.
     */
//    private final List<User> users;

    /**
     * Constructor initializes the list of users.
     */
    public UserService() {
//        this.users = List.of(
//                new User("andrei", "1234", "Андрей", "Сергеев", Collections.singleton(Role.USER)),
//                new User("petr", "12345", "Петр", "Иванов", Collections.singleton(Role.ADMIN)),
//                new User("serg", "123", "Сергей", "Макаров", Collections.singleton(Role.EDITOR))
//        );
    }

    /**
     * Fetches a user based on the login credentials provided.
     * <p>
     * This method iterates through the list of users and returns an {@link Optional} of {@link UserDto}
     * if a user with the specified login is found.
     * </p>
     *
     * @param login the login credentials of the user.
     * @return an Optional of User if a user with the specified login is found, otherwise an empty Optional.
     */
    public Optional<UserDto> getByLogin(@NonNull String login) {
//        return users.stream()
//                .filter(user -> login.equals(user.getLogin()))
//                .findFirst();
        return getByLoginWithDb(login); // поменял поиск на бд
    }

    // с БД
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    void createUser() throws ResponseException {
        UserDto newUser = new UserDto("user","1234","Test","Test",Set.of(Role.USER));
        this.createUser(newUser);
    }

    public Optional<UserDto> getByLoginWithDb(@NonNull String login) {
        List<UserEntity> userEntities = userRepository.findByLogin(login);
        Optional<UserDto> user = Optional.empty();
        if(userEntities !=null && !userEntities.isEmpty()) {
            UserEntity userEntity = userEntities.get(0); // берем первый
            user = Optional.of(UserDto.builder()
                    .login(userEntity.getLogin())
                    .password(userEntity.getPassword())
                    .firstName(userEntity.getFirstName())
                    .lastName(userEntity.getLastName())
                    .roles(
                            Arrays.stream(userEntity.getRoles().split(","))
                                    .distinct()
                                    .map(s -> Role.valueOf(s))
                                    .collect(Collectors.toSet())
                    ).build());
        }
        return user;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserDto createUser(UserDto userDto) throws ResponseException {
        UserDto returnUserDto = null;
        List<UserEntity> userEntities = userRepository.findByLogin(userDto.getLogin());
        if(userEntities==null || userEntities.isEmpty()) {
//            Set<Role> roles = userDto.getRoles()==null ? new HashSet<Role>(Arrays.asList(Role.USER)) : userDto.getRoles();
            Set<Role> roles = userDto.getRoles()==null ? Set.of(Role.USER) : userDto.getRoles();
            String rolesStr = roles.stream().map(role -> role.toString()).collect(Collectors.joining(","));
            UserEntity userEntity = UserEntity.builder()
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .login(userDto.getLogin())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .roles(rolesStr)
                    .createdAt(ZonedDateTime.now())
                    .build();
            UserEntity userEntityResponse = userRepository.save(userEntity);
            if(userEntityResponse != null) {
//                Set<String> mySet = new HashSet<String>(Arrays.asList(rolesStr.split(",")));
                Set<String> mySet = Set.of(rolesStr.split(","));
                returnUserDto = UserDto.builder()
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getLastName())
                        .login(userEntity.getLogin())
                        .password(userEntity.getPassword())
                        .roles(mySet.stream().map(role -> Role.valueOf(role)).collect(Collectors.toSet()))
                        .build();
             }
        }
        else
            throw new ResponseException("Пользователь с логином "+userDto.getLogin()+" уже существует!");
        return returnUserDto;
    }
}


