package quiz_system.dao;

import quiz_system.domain.QuizQuestion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eric Wang
 * @date 2/6/23 1:30 PM
 */
@Component
public class QuizQuestionRowMapper implements RowMapper<QuizQuestion> {
    @Override
    public QuizQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuiz_id(rs.getInt("quiz_id"));
        quizQuestion.setQuestion_id(rs.getInt("question_id"));
        quizQuestion.setUser_choice_id(rs.getInt("user_choice_id"));
        return quizQuestion;
    }
}