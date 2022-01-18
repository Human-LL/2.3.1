package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User addUser(User user);

    User updateUser(Long id, User user);

    User deleteUser(Long id);
}