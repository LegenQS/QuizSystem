package quiz_system.service;

import quiz_system.dao.QuestionDao;
import quiz_system.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getAllQuestion() {
        return questionDao.getAllQuestion();
    }

    public List<Question> getQuestionByCategory(Integer cat_id) {
        return questionDao.getQuestionByCategory(cat_id);
    }

    public Question getQuestionByID(int id) {
        return questionDao.getQuestionByID(id);
    }

    public Integer getNextID() {
        return questionDao.getNextID();
    }

    public Integer insertQuestion(Integer cat_id, String description) {
        return questionDao.insertQuestion(cat_id, description);
    }

    public void updateQuestionByID(int question_id, int cat_id, String description) {
        questionDao.updateQuestionByID(question_id, cat_id, description);
    }
//    public Question getQuestion() {
//        return questionDao.getQuestion();
//    }
//
//    public String checkAnswer(Choice selectedChoice) {
//        Question question = questionDao.getQuestion();
//        Choice correctChoice = question.getChoices().get(question.getCorrectChoiceId() - 1);
//        return selectedChoice.equals(correctChoice) ? "Correct!" : "Incorrect";
//    }
//
//    public Optional<Choice> getChoiceById(Integer selectedChoiceId) {
//        return questionDao
//                .getQuestion()
//                .getChoices()
//                .stream()
//                .filter(choice -> choice.getId() == selectedChoiceId)
//                .findFirst();
//    }
}
