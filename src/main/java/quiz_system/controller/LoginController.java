package quiz_system.controller;

import quiz_system.domain.User;
import quiz_system.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        System.out.println("get login, session status: " + session == null);
        // redirect to /user_home if user is already logged in
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/user_home";
        }

        return "login";
    }

    // validate that we are always getting a new session after login
    @PostMapping("/login")
    public String postLogin(@RequestParam String username,
                            @RequestParam String password,
                            HttpServletRequest request) {

        Optional<User> possibleUser = loginService.validateLogin(username, password);

        if(possibleUser.isPresent()) {
            HttpSession oldSession = request.getSession(false);
            // invalidate old session if it exists
            if (oldSession != null) oldSession.invalidate();

            // if user not activated
            if (!possibleUser.get().is_active()) return "login";

            // generate new session
            HttpSession newSession = request.getSession(true);

            // store user details in session
            newSession.setAttribute("user", possibleUser.get());
            if (username.equals("admin")) {
                return "redirect:/admin_home";
            }
            return "redirect:/user_home";
        } else { // if user details are invalid
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();

        return "login";
    }

    @GetMapping("/register")
    public String getToRegister(HttpServletRequest request, Model model) {
        System.out.println("get register request in login page");
        return "register";
    }

    @GetMapping("/user_home")
    public String getToUserHome(HttpServletRequest request, Model model) {
        System.out.println("get to user home page");
        return "user_home";
    }
}
