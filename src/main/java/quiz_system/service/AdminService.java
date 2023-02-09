package quiz_system.service;

import quiz_system.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/4/23 6:02 PM
 */
@Service
public class AdminService {
    private final UserService userService;

    @Autowired
    public AdminService(UserService userService) {this.userService = userService; }

    public List<User> getAllUser() {
        return userService.getAllUsers();
    }


}