package org.example.service;


import org.example.dto.UserDto;
import org.example.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    UserDto get(long id);
    void save(User user);
    UserDto get(String login);
    boolean exists(String login, String password);
    void saveLoginToken(String login, String loginToken);
    String getLoginByToken(String token);
    void updateUser(String login, UserDto user);
    UserDto getByName(String username);
}
