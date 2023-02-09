package quiz_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quiz_system.domain.*;
import quiz_system.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Eric Wang
 * @date 2/4/23 6:06 PM
 */
@Controller
public class AdminController {
    private final UserService userService;
    private final QuestionService questionService;
    private final OptionService optionService;
    private final ContactService contactService;
    private final FeedBackService feedBackService;
    private final CategoryService categoryService;

    public AdminController(UserService userService, QuestionService questionService,
                           OptionService optionService, ContactService contactService,
                           FeedBackService feedBackService, CategoryService categoryService) {
        this.userService = userService;
        this.questionService = questionService;
        this.optionService = optionService;
        this.contactService = contactService;
        this.feedBackService = feedBackService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin_user_page")
    public String getUserPage(HttpServletRequest request, Model model) {
        System.out.println("getting to user page");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/quiz_home";
        }
        System.out.println("getting to user page");
        List<User> user = userService.getAllUsers();
        model.addAttribute("all_user", user);
        return "admin_user_page";
    }

    @PostMapping("/user_activate")
    public String activateUser(
            @RequestParam(name = "user_id") Integer updateID,
            HttpSession session) {
        session.setAttribute("user_id", updateID);
        userService.updateUserStatus(updateID);
        return "redirect:/admin_user_page";
    }

    @GetMapping("/quiz_home")
    public String getQuizHome(HttpServletRequest request, Model model) {
        System.out.println("getting to quiz home page");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        List<Question> questions = questionService.getAllQuestion();
        if (model.getAttribute("questions") != null) return "redirect:/quiz_home";

        model.addAttribute("questions", questions);
        return "quiz_home";
    }

    @GetMapping("/admin_quiz_home")
    public String getAdminQuizHome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        return "admin_quiz_home";
    }

    @GetMapping("/admin_quiz_question")
    public String getQuizQuestion(HttpServletRequest request, Model model) {
        System.out.println("getting to quiz home page");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        List<Question> questions = questionService.getAllQuestion();
        if (model.getAttribute("questions") != null) return "redirect:/admin_quiz_home";

        model.addAttribute("questions", questions);
        return "admin_quiz_question";
    }

    //PostMapping of admin_quiz_question
    @PostMapping("/admin_quiz_question")
    public String postQuizQuestion(
            @RequestParam(name = "selectedQuestionID") Integer selectedQuestionID,
            HttpSession session) {

        List<Option> options = optionService.getOptionByQuestionID(selectedQuestionID);
        Question question = questionService.getQuestionByID(selectedQuestionID);
        QuestionOption questionOption = new QuestionOption();
        questionOption.setQuestion(question);
        questionOption.setOptions(options);

        session.setAttribute("questionOption", questionOption);
        return "redirect:/admin_question_detail";
    }

    @GetMapping("/admin_question_detail")
    public String getAdminQuestionDetail(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        QuestionOption questionOption = (QuestionOption) session.getAttribute("questionOption");
        session.removeAttribute("questionOption");
        model.addAttribute("questionOption", questionOption);
        model.addAttribute("categories", categoryService.getCategory());

        return "admin_question_detail";
    }

    @PostMapping("/admin_question_detail")
    public String postUpdateQuizQuestion(
            @RequestParam(name = "description") String description,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "option0") String option1,
            @RequestParam(name = "option1") String option2,
            @RequestParam(name = "option2") String option3,
            @RequestParam(name = "option3") String option4,
            @RequestParam(name = "question_id") Integer question_id,
            @RequestParam(name = "optionID0") Integer optionID1,
            @RequestParam(name = "optionID1") Integer optionID2,
            @RequestParam(name = "optionID2") Integer optionID3,
            @RequestParam(name = "optionID3") Integer optionID4,
            @RequestParam(name = "correct_answer") Integer correct_ans,
            HttpSession session) {

        Category category1 = categoryService.getCategoryByName(category);
        int cat_id = category1.getCat_id();

        System.out.println(question_id);
        System.out.println(category1);

        System.out.println(option1);
        System.out.println(optionID1);

        System.out.println(option2);
        System.out.println(optionID2);

        System.out.println(option3);
        System.out.println(optionID3);

        System.out.println(option4);
        System.out.println(optionID4);

        questionService.updateQuestionByID(question_id, cat_id, description);
        optionService.updateOptionByID(optionID1, option1, correct_ans == optionID1);
        optionService.updateOptionByID(optionID2, option2, correct_ans == optionID2);
        optionService.updateOptionByID(optionID3, option3, correct_ans == optionID3);
        optionService.updateOptionByID(optionID4, option4, correct_ans == optionID4);

        return "redirect:/admin_quiz_question";
    }

    @GetMapping("/admin_create_quiz")
    public String getCreateQuizQuestion(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        List<Category> categories = categoryService.getCategory();
        model.addAttribute("categories", categories);
        return "admin_create_quiz";
    }

    @PostMapping("/admin_create_quiz")
    public String postCreateQuizQuestion(
            @RequestParam(name = "description") String description,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "option1") String option1,
            @RequestParam(name = "option2") String option2,
            @RequestParam(name = "option3") String option3,
            @RequestParam(name = "option4") String option4,
            @RequestParam(name = "correct_answer") Integer correct_ans,
            HttpSession session) {
        System.out.println(description);
        System.out.println(category);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(correct_ans);

        int cat_id = categoryService.getCategoryByName(category).getCat_id();
        int question_id = questionService.insertQuestion(cat_id, description);
        System.out.println(question_id);

        optionService.insertOption(question_id, option1, correct_ans == 1);
        optionService.insertOption(question_id, option2, correct_ans == 2);
        optionService.insertOption(question_id, option3, correct_ans == 3);
        optionService.insertOption(question_id, option4, correct_ans == 4);

        return "redirect:/admin_create_quiz";
    }

    @PostMapping("/quiz_home")
    public String createNewQuiz(
            @RequestParam(name = "description") String description,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "option1") String option1,
            @RequestParam(name = "option2") String option2,
            @RequestParam(name = "option3") String option3,
            @RequestParam(name = "option4") String option4,
            @RequestParam(name = "correct_answer") Integer correct_ans,
            HttpSession session) {
        System.out.println(description);
        System.out.println(category);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(correct_ans);

        int cat_id = categoryService.getCategoryByName(category).getCat_id();
        int question_id = questionService.insertQuestion(cat_id, description);
        System.out.println(question_id);

        optionService.insertOption(question_id, option1, correct_ans == 1);
        optionService.insertOption(question_id, option2, correct_ans == 2);
        optionService.insertOption(question_id, option3, correct_ans == 3);
        optionService.insertOption(question_id, option4, correct_ans == 4);

        return "redirect:/quiz_home";
    }


    @GetMapping("/admin_home")
    public String getAdminHome(HttpServletRequest request, Model model) {
        System.out.println("redirecting to admin home page");
        return "/admin_home";
    }

    @GetMapping("/admin_contact")
    public String getContact(HttpServletRequest request, Model model) {
        List<Contact> contact = contactService.getAllContact();
        model.addAttribute("contacts", contact);
        System.out.println(contact.size());
        return "/admin_contact";
    }

    @GetMapping("/admin_feedback")
    public String getFeedBack(HttpServletRequest request, Model model) {
        List<FeedBack> feedBacks = feedBackService.getAllFeedBack();
        model.addAttribute("feedbacks", feedBacks);
        System.out.println(feedBacks.size());
        return "/admin_feedback";
    }

}
