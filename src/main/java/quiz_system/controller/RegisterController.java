package quiz_system.controller;

import quiz_system.domain.User;
import quiz_system.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String email,
                            @RequestParam String first_name,
                            @RequestParam String last_name,
                            @RequestParam String address,
                            @RequestParam String phone,
                            HttpServletRequest request) {

        System.out.println("get into register");
        System.out.println(username + " " + password + " " + phone);
        boolean is_admin = false;
        boolean is_activate = true;
        Optional<User> registered = registerService.getRegisteredUser(username);

        if (registered != null && registered.isPresent()) {
            System.out.println("user exists!");
            return "redirect:/register";
        }

        if (username.equals("admin")) {
            is_admin = true;
        }

        User user = new User();
        user.setUsername(username);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setAddress(address);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.set_admin(is_admin);
        user.set_active(is_activate);

        System.out.println("getting into database handler");
        registerService.createNewUser(user);

        System.out.println("create new user!");
        return "login";
    }
}
