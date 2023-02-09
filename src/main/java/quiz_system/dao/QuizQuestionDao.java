package quiz_system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Eric Wang
 * @date 2/6/23 1:01 PM
 */
@Repository
public class QuizQuestionDao {
    private JdbcTemplate jdbcTemplate;
    private QuizQuestionRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizQuestionDao(JdbcTemplate jdbcTemplate, QuizQuestionRowMapper rowMapper,
                   NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createNewQuizQuestion(int quiz_id, List<Integer> question_id, List<Integer> user_choice_id) {
        String query = "insert into QuizQuestion values(?, ?, ?)";
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, quiz_id);
                ps.setInt(2, question_id.get(i));
                ps.setInt(3, user_choice_id.get(i));
            }

            @Override
            public int getBatchSize() {
                return question_id.size();
            }
        });
    }
}

