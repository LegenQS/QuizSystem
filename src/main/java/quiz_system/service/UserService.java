package quiz_system.service;

import quiz_system.dao.UserDao;
import quiz_system.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) { this.userDao = userDao; }

    public void createNewUser(User user) {
        userDao.createNewUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public Optional<User> getUserByName(String username) {
//        System.out.println("get the record for name " + username);
//        if (userDao.getUserByName(username) == null) {
//            System.out.println("database is empty");
//            return null;
//        }
//        System.out.println("database is not empty");
        return userDao.getAllUsers().stream()
                .filter(a -> a.getUsername().equals(username))
                .findFirst();
    }

    public Optional<User> validateLogin(String username, String password) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getUsername().equals(username)
                        && a.getPassword().equals(password))
                .findAny();
    }

    public void updateUserStatus(int user_id) {
        userDao.updateUserStatus(user_id);
    }

}
