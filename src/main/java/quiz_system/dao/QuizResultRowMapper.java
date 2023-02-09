package quiz_system.dao;

import quiz_system.domain.QuizResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eric Wang
 * @date 2/6/23 4:37 PM
 */
@Component
public class QuizResultRowMapper implements RowMapper<QuizResult> {
    @Override
    public QuizResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizResult quizResult = new QuizResult();
        quizResult.setUser_choice_id(rs.getInt("user_choice_id"));
        quizResult.setOption_id(rs.getInt("option_id"));
        quizResult.setQuestion_des(rs.getString("question_des"));
        quizResult.setOption_des(rs.getString("option_des"));
        quizResult.set_correct(rs.getBoolean("is_correct"));
        return quizResult;
    }
}
