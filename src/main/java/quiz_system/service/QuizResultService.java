package quiz_system.service;

import quiz_system.dao.QuizResultDao;
import quiz_system.domain.QuizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/6/23 4:41 PM
 */
@Service
public class QuizResultService {
    private final QuizResultDao quizResultDao;

    @Autowired
    public QuizResultService(QuizResultDao quizResultDao) {
        this.quizResultDao = quizResultDao;
    }

    public List<QuizResult> getQuizResultByID(int quiz_id) {
        return quizResultDao.getQuizResultByID(quiz_id);
    }
}
