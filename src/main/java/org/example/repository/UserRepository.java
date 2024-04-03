package org.example.repository;

import org.example.dto.UserDto;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface
UserRepository  extends JpaRepository<User, Long> {
    Optional<User> getUserByLogin(String login);

    User getUserByLoginToken(String token);

    User getUserByName(String username);

}
