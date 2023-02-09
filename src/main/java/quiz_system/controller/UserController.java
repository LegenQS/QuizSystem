package quiz_system.controller;

import quiz_system.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quiz_system.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Eric Wang
 * @date 2/5/23 3:34 AM
 */
@Controller
public class UserController {
    private final UserService userService;
    private final ContactService contactService;
    private final FeedBackService feedBackService;
    private final QuestionService questionService;
    private final OptionService optionService;

    public UserController(UserService userService, ContactService contactService,
                          FeedBackService feedBackService, QuestionService questionService,
                          OptionService optionService) {
        this.userService = userService;
        this.contactService = contactService;
        this.feedBackService = feedBackService;
        this.questionService = questionService;
        this.optionService = optionService;
    }

    @GetMapping("user_contact")
    public String getToContact(HttpServletRequest request, Model model) {
        return "user_contact";
    }

    @PostMapping("user_contact")
    public String postContact(
            @RequestParam(name = "message") String message,
            HttpSession session) {
        User user = (User) session.getAttribute("user");

        System.out.println(user);

        contactService.createNewContact(user.getUser_id(), message, session.getLastAccessedTime());
        return "redirect:/user_home";
    }

    @GetMapping("user_feedback")
    public String getToFeedBack(HttpServletRequest request, Model model) {
        return "user_feedback";
    }

    @PostMapping("user_feedback")
    public String createFeedBack(
            @RequestParam(name = "rating") Integer rating,
            @RequestParam(name = "message") String message,
            HttpSession session) {

        feedBackService.createNewFeedBack(rating, message);
        return "redirect:/user_home";
    }

    @GetMapping("common_feedback")
    public String getCommonFeedBack(HttpServletRequest request, Model model) {
        return "common_feedback";
    }

    @PostMapping("common_feedback")
    public String createCommonFeedBack(
            @RequestParam(name = "rating") Integer rating,
            @RequestParam(name = "message") String message,
            HttpSession session) {

        feedBackService.createNewFeedBack(rating, message);
        return "redirect:/login";
    }


}

