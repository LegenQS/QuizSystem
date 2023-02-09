package quiz_system.dao;

import quiz_system.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao {
    private JdbcTemplate jdbcTemplate;
    private QuestionRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuestionDao(JdbcTemplate jdbcTemplate, QuestionRowMapper rowMapper,
                   NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Question getQuestionByID(int question_id) {
        String query = "select * from Question " +
                        "where question_id = ?";
        List<Question> question = jdbcTemplate.query(query, rowMapper, question_id);
        return question.size() == 0 ? null : question.get(0);
    }

    public List<Question> getAllQuestion() {
        String query = "select * from Question";
        return jdbcTemplate.query(query, rowMapper);
    }

    public Integer getNextID() {
        String query = "SELECT `AUTO_INCREMENT` " +
                "FROM  INFORMATION_SCHEMA.TABLES " +
                "WHERE TABLE_SCHEMA = person_assignment " +
                "AND   TABLE_NAME   = Question";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public Integer insertQuestion(Integer cat_id, String description) {
        String query1 = "insert into Question (cat_id, description) values(?, ?)";
        String query2 = "select last_insert_id()";
        jdbcTemplate.update(query1, cat_id, description);
        return jdbcTemplate.queryForObject(query2, Integer.class);
    }

    public void updateQuestionByID(int question_id, int cat_id, String description) {
        String query = "update Question " +
                       "Set description = ?, cat_id = ? " +
                       "where question_id = ?";
        jdbcTemplate.update(query, description, cat_id, question_id);
    }

    public List<Question> getQuestionByCategory(Integer cat_id) {
        String query = "select * from Question where cat_id = ? order by RAND() limit 5";
        return jdbcTemplate.query(query, rowMapper, cat_id);
    }
}
