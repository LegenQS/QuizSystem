package quiz_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quiz_system.domain.*;
import quiz_system.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class QuizController {
    private final QuestionService questionService;
    private final OptionService optionService;
    private final CategoryService categoryService;
    private final QuizService quizService;
    private final QuizQuestionService quizQuestionService;
    private final QuizResultService quizResultService;

    @Autowired
    public QuizController(QuestionService questionService, OptionService optionService,
                          CategoryService categoryService, QuizService quizService,
                          QuizQuestionService quizQuestionService,
                          QuizResultService quizResultService) {
        this.questionService = questionService;
        this.optionService = optionService;
        this.categoryService = categoryService;
        this.quizService = quizService;
        this.quizQuestionService = quizQuestionService;
        this.quizResultService = quizResultService;
    }

    @GetMapping("user_quiz")
    public String getToQuizHome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        model.addAttribute("categories", categoryService.getCategory());
        model.addAttribute("user", (User) session.getAttribute("user"));

        return "user_quiz";
    }

    @PostMapping("user_quiz")
    public String postQuizCategory(
            @RequestParam(name = "selectedCategoryID") Integer selectedCategoryID,
            @RequestParam(name = "quiz_name") String quiz_name,
            HttpSession session) {
        System.out.println(selectedCategoryID);
        session.setAttribute("selectedCategoryID", selectedCategoryID);
        session.setAttribute("quiz_name", quiz_name);

        return "redirect:/user_quiz_start";
    }

    @GetMapping("user_quiz_start")
    public String startQuiz(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        Integer selectedCategoryID = (Integer) session.getAttribute("selectedCategoryID");
        System.out.println(selectedCategoryID);

        List<Question> question = questionService.getQuestionByCategory(selectedCategoryID);
        if (question == null || question.size() == 0)
            return "redirect:/user_quiz";

        List<Integer> question_ids = getQuestionsID(question);
        List<List<Option>> options = optionService.getOptionByQuestionIDs(question_ids);
        List<QuestionOption> questionOptions = combineQuestionOption(question, options);

        model.addAttribute("questionOption", questionOptions);
        session.setAttribute("start_time", System.currentTimeMillis());

        return "user_quiz_start";
    }

    @PostMapping("user_quiz_start")
    public String postQuiz(
            @RequestParam(name = "question1") Integer questionID1,
            @RequestParam(name = "user_choice1") Integer selectedOptionID1,
            @RequestParam(name = "question2") Integer questionID2,
            @RequestParam(name = "user_choice2") Integer selectedOptionID2,
            @RequestParam(name = "question3") Integer questionID3,
            @RequestParam(name = "user_choice3") Integer selectedOptionID3,
            @RequestParam(name = "question4") Integer questionID4,
            @RequestParam(name = "user_choice4") Integer selectedOptionID4,
            @RequestParam(name = "question5") Integer questionID5,
            @RequestParam(name = "user_choice5") Integer selectedOptionID5,
            HttpSession session) {

        // start and end time need to double-check !
        long end_time = System.currentTimeMillis();
        long start_time = (long) session.getAttribute("start_time");
        User user = (User) session.getAttribute("user");
        int cat_id = (int) session.getAttribute("selectedCategoryID");
        String quiz_name = (String) session.getAttribute("quiz_name");

        System.out.println(new Timestamp(start_time));
        System.out.println(new Timestamp(end_time));
        System.out.println(user.getUser_id());
        System.out.println(quiz_name);

        System.out.println(questionID1 + " " + selectedOptionID1);
        System.out.println(questionID2 + " " + selectedOptionID2);
        System.out.println(questionID3 + " " + selectedOptionID3);
        System.out.println(questionID4 + " " + selectedOptionID4);
        System.out.println(questionID5 + " " + selectedOptionID5);

        List<Integer> user_options = Arrays.asList(
                selectedOptionID1, selectedOptionID2, selectedOptionID3,
                selectedOptionID4, selectedOptionID5);
        List<Integer> question_ids = Arrays.asList(
                questionID1, questionID2, questionID3, questionID4, questionID5);
        int score = getFinalScore(user_options);

        // 1. post to Quiz table (get quiz_id back)
        int quiz_id = quizService.createNewQuiz(user.getUser_id(), cat_id,
                quiz_name, start_time, end_time, score);

        // 2. post to QuizQuestion table
        quizQuestionService.createNewQuizQuestion(quiz_id, question_ids, user_options);

        session.setAttribute("quiz_id", quiz_id);

        return "redirect:/user_quiz_result";
    }

    @GetMapping("user_quiz_result")
    public String getQuizResult(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        int quiz_id = (int) session.getAttribute("quiz_id");
        Quiz quiz = quizService.getQuizByID(quiz_id);
        int score = quiz.getScore();

        List<QuizResult> quizResult = quizResultService.getQuizResultByID(quiz_id);

        model.addAttribute("score", score);
        model.addAttribute("quizResults", quizResult);

        return "user_quiz_result";
    }

    @GetMapping("/admin_quiz")
    public String getAllQuizResult(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String orderBy = null, order = null;

        if (session.getAttribute("orderBy") != null) {
            orderBy = (String) session.getAttribute("orderBy");
            order = (String) session.getAttribute("order");
            session.removeAttribute("orderBy");
            session.removeAttribute("order");
        }
        System.out.println(orderBy);

        List<Quiz> quizzes = quizService.getAllQuiz(orderBy, order);
        model.addAttribute("quizzes", quizzes);

        return "admin_quiz";
    }

    @PostMapping("/admin_quiz")
    public String getQuizResultDetail(
            @RequestParam(name = "orderBy") String orderBy,
            @RequestParam(name = "order") String order,
            HttpSession session) {

        session.setAttribute("orderBy", orderBy);
        session.setAttribute("order", order);

        return "redirect:/admin_quiz";
    }

    @GetMapping("/admin_quiz_details")
    public String getUserQuizResultAdmin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        int quiz_id = (Integer) session.getAttribute("selectedQuizID");
        session.removeAttribute("selectedQuizID");
        List<QuizResult> quizResult = quizResultService.getQuizResultByID(quiz_id);
        model.addAttribute("quizResults", quizResult);

        return "admin_quiz_details";
    }

    @PostMapping("/admin_quiz_details")
    public String PostUserQuizResultDetailAdmin(
            @RequestParam(name = "selectedQuizID") Integer selectedQuizID,
            HttpSession session) {
        session.setAttribute("selectedQuizID", selectedQuizID);

        return "redirect:/admin_quiz_details";
    }

    @GetMapping("/user_quiz_results")
    public String getUserQuizResult(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null)
            return "redirect:/login";

        User user = (User) session.getAttribute("user");
        Integer filterCategoryID = null;
        if (session.getAttribute("filterCategoryID") != null) {
            filterCategoryID = (Integer) session.getAttribute("filterCategoryID");
            session.removeAttribute("filterCategoryID");
        }

        List<Quiz> quizzes = quizService.
                getQuizByUserIDWithFilter(user.getUser_id(), filterCategoryID);

        model.addAttribute("quizzes", quizzes);
        model.addAttribute("categories", categoryService.getCategory());

        return "user_quiz_results";
    }

    @PostMapping("/user_quiz_results")
    public String postUserQuizResultFilter(
            @RequestParam(name = "filterCategoryID") Integer filterCategoryID,
            HttpSession session) {

        session.setAttribute("filterCategoryID", filterCategoryID);

        return "redirect:/user_quiz_results";
    }

    @PostMapping("/user_quiz_result_detail")
    public String postUserQuizResultDetail(
            @RequestParam(name = "selectedQuizID") Integer selectedQuizID,
            HttpSession session) {

        session.setAttribute("quizID", selectedQuizID);

        return "redirect:/user_quiz_result_detail";
    }

    @GetMapping("/user_quiz_result_detail")
    public String getUserQuizResultDetail(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        int quiz_id = (Integer) session.getAttribute("quizID");
        List<QuizResult> quizResult = quizResultService.getQuizResultByID(quiz_id);
        model.addAttribute("quizResults", quizResult);

        return "user_quiz_result_detail";
    }

    private List<QuestionOption> combineQuestionOption(List<Question> question,
                                                       List<List<Option>> options) {
        List<QuestionOption> questionOptions = new ArrayList<>();
        for (int i = 0; i < question.size(); i++) {
            questionOptions.add(new QuestionOption(question.get(i),
                    options.get(i), i+1, null));
        }

        return questionOptions;
    }

    private List<Integer> getQuestionsID(List<Question> question) {
        List<Integer> ids = new ArrayList<>();
        for (Question q : question) {
            ids.add(q.getQuestion_id());
        }

        return ids;
    }

    private int getFinalScore(List<Integer> ids) {
        int score = 0;
        List<Option> ops = optionService.getOptionByIDs(ids);

        for (Option op : ops) {
            if (op.is_correct()) score++;
        }

        return score;
    }

    private List<List<QuizResult>> divideByGroup(List<QuizResult> quizResult) {
        List<List<QuizResult>> quizResults = new ArrayList<>();
        List<QuizResult> qr_list = null;
        int qid = -1;

        if (quizResult == null || quizResult.size() == 0) return quizResults;

        for (QuizResult qr : quizResult) {
            if (qid != qr.getOption_id()) {
                qid = qr.getOption_id();

                if (qr_list == null || !qr_list.isEmpty())
                    qr_list = new ArrayList<>();
                else
                    quizResults.add(qr_list);
                qr_list.add(qr);
            }
        }

        if (!qr_list.isEmpty())
            quizResults.add(qr_list);

        return quizResults;
    }
}
