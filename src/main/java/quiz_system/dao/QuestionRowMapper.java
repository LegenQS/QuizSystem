package quiz_system.dao;

import quiz_system.domain.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eric Wang
 * @date 2/4/23 4:45 PM
 */
@Component
public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setQuestion_id(rs.getInt("question_id"));
        question.setCat_id(rs.getInt("cat_id"));
        question.setDescription(rs.getString("description"));
        return question;
    }
}