package de.telran.urlshortener.security.repository;


import de.telran.urlshortener.security.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.login=?1")
    List<UserEntity> findByLogin(String login);
}
