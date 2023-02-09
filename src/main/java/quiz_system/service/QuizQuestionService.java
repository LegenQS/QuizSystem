package quiz_system.service;

import quiz_system.dao.QuizQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/6/23 1:00 PM
 */
@Service
public class QuizQuestionService {
    private final QuizQuestionDao questionDao;

    @Autowired
    public QuizQuestionService(QuizQuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public void createNewQuizQuestion(int quiz_id, List<Integer> question_id, List<Integer> user_choice_id) {
        questionDao.createNewQuizQuestion(quiz_id, question_id, user_choice_id);
    }
}
