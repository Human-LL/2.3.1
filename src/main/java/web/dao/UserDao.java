package web.dao;


import web.model.User;

import java.util.*;

public interface UserDao {

    List<User> allUser();

    Optional<User> findById(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteById(Long id);
}