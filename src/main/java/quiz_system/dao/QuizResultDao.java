package quiz_system.dao;

import quiz_system.domain.QuizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/6/23 4:36 PM
 */
@Repository
public class QuizResultDao {
    private JdbcTemplate jdbcTemplate;
    private QuizResultRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizResultDao(JdbcTemplate jdbcTemplate, QuizResultRowMapper rowMapper,
                         NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<QuizResult> getQuizResultByID(int quiz_id) {
        String query = "select a.user_choice_id, b.description as question_des, \n" +
                "c.description as option_des, c.is_correct, c.option_id\n" +
                "from QuizQuestion as a, Question as b, `Option` as c\n" +
                "where a.question_id = b.question_id and c.question_id = b.question_id and a.quiz_id = ?";
        QuizResult s = new QuizResult();
//        s.getUser_choice_id()
        return jdbcTemplate.query(query, rowMapper, quiz_id);
    }
}
