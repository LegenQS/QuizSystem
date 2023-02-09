package quiz_system.service;

import quiz_system.dao.QuizDao;
import quiz_system.domain.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/4/23 5:01 PM
 */
@Service
public class QuizService {
    private final QuestionService questionService;
    private final QuizDao quizDao;

    @Autowired
    public QuizService(QuestionService questionService, QuizDao quizDao) {
        this.questionService = questionService;
        this.quizDao = quizDao;
    }

    public int createNewQuiz(int user_id, int cat_id, String quiz_name, long start_time, long end_time, int score) {
        return quizDao.createNewQuiz(user_id, cat_id, quiz_name, start_time, end_time, score);
    }

    public Quiz getQuizByID(int quiz_id) {
        return quizDao.getQuizByID(quiz_id);
    }

    public List<Quiz> getAllQuiz(String filter, String order) {
        return quizDao.getAllQuiz(filter, order);
    }

    public List<Quiz> getQuizByUserIDWithFilter(Integer user_id, Integer filterCategoryID) {
        return quizDao.getQuizByUserIDWithFilter(user_id, filterCategoryID);
    }

//    public Optional<User> validateLogin(String username, String password) {
//        return questionService.validateLogin(username, password);
//    }
//
//    public Optional<User> getRegisteredUser (String username) {
//        return questionService.getUserByName(username);
//    }
//
//    public void createNewUser (User user) {
//        questionService.createNewUser(user);
//    }

}