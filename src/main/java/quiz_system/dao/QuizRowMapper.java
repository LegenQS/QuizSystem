package quiz_system.dao;

import quiz_system.domain.Quiz;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eric Wang
 * @date 2/6/23 1:03 PM
 */
@Component
public class QuizRowMapper implements RowMapper<Quiz> {
    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();
        quiz.setQuiz_id(rs.getInt("quiz_id"));
        quiz.setUser_id(rs.getInt("user_id"));
        quiz.setCat_id(rs.getInt("cat_id"));
        quiz.setQuiz_name(rs.getString("quiz_name"));
        quiz.setStart_time(rs.getTimestamp("start_time"));
        quiz.setEnd_time(rs.getTimestamp("end_time"));
        quiz.setScore(rs.getInt("score"));
        return quiz;
    }
}