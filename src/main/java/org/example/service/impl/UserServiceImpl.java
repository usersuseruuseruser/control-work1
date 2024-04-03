package org.example.service.impl;

import org.example.dto.UserDto;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(
                u -> new UserDto(u.getId(), u.getName(), u.getEmail(), u.getSelfInfo(), u.getProfilePictureURl())
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto get(long id) {
        return userRepository.findById(id).map(
                u -> new UserDto(u.getId(), u.getName(), u.getEmail(), u.getSelfInfo(), u.getProfilePictureURl())
        ).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDto get(String login) {
        Optional<User> user = userRepository.getUserByLogin(login);
        return user.map(value -> new UserDto(value.getId(), value.getName(), value.getEmail(), value.getSelfInfo(), value.getProfilePictureURl())).orElse(null);
    }

    @Override
    public boolean exists(String login, String password) {
        Optional<User> userByLogin = userRepository.getUserByLogin(login);
        return userByLogin.isPresent() && userByLogin.get().getPassword().equals(password);
    }

    @Override
    public void saveLoginToken(String login, String loginToken) {
        Optional<User> user = userRepository.getUserByLogin(login);
        if (!user.isPresent()) {
            return;
        }
        user.get().setLoginToken(loginToken);
        userRepository.save(user.get());
    }

    @Override
    public String getLoginByToken(String token) {
        return userRepository.getUserByLoginToken(token).getLogin();
    }

    @Override
    public void updateUser(String login, UserDto user) {
        Optional<User> userByLogin = userRepository.getUserByLogin(login);
        if (!userByLogin.isPresent()) {
            return;
        }
        else{
            userByLogin.get().setName(user.getName());
            userByLogin.get().setEmail(user.getEmail());
            userByLogin.get().setSelfInfo(user.getSelfInfo());
            userByLogin.get().setProfilePictureURl(user.getProfilePictureUrl());
            userRepository.save(userByLogin.get());
        }
    }

    @Override
    public UserDto getByName(String username) {
        User user = userRepository.getUserByName(username);
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getSelfInfo(), user.getProfilePictureURl());
    }
}
