package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userDao.getUserById(id);
        if (existingUser == null) {
            return null;
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userDao.updateUser(existingUser);
    }

    @Override
    public User deleteUser(Long id) {
        if (userDao.getUserById(id) == null) {
            return null;
        }
        return userDao.deleteUser(id);
    }
}