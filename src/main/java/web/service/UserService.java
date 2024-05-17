package web.service;

import web.model.User;

import java.util.List;

import java.util.Optional;

public interface UserService {

    List<User> allUser();

    Optional<User> findById(Long id);

    User saveUser(User user);

    User updateUser(User user);

    void deleteById(Long id);
}
