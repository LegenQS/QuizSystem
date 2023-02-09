package quiz_system.dao;

import quiz_system.domain.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Eric Wang
 * @date 2/6/23 12:39 PM
 */
@Repository
public class QuizDao {
    private JdbcTemplate jdbcTemplate;
    private QuizRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizDao(JdbcTemplate jdbcTemplate, QuizRowMapper rowMapper,
                   NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int createNewQuiz(int user_id, int cat_id, String quiz_name, long start_time, long end_time, int score) {
        String queryID = "select last_insert_id()";
        String query = "insert into Quiz (user_id, cat_id, quiz_name, start_time, " +
                "end_time, score) values(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, user_id, cat_id, quiz_name,
                new Timestamp(start_time), new Timestamp(end_time), score);

        return jdbcTemplate.queryForObject(queryID, Integer.class);
    }

    public Quiz getQuizByID(int quiz_id) {
        String query = "select * from Quiz where quiz_id = ?";
        List<Quiz> quiz = jdbcTemplate.query(query, rowMapper, quiz_id);

        return quiz.size() == 0 ? null : quiz.get(0);
    }

    public List<Quiz> getAllQuiz(String filter, String order) {
        if (filter == null || filter.length() == 0) {
            filter = "start_time";
        }

        if (order == null || order.length() == 0) {
            order = "desc";
        }

        String query = String.format("select * from Quiz order by %s %s", filter, order);
        return jdbcTemplate.query(query, rowMapper);
    }

    public List<Quiz> getQuizByUserIDWithFilter(Integer user_id, Integer filterCategoryID) {
        if (filterCategoryID == null) {
            String query = "select * from Quiz where user_id = ?";
            return jdbcTemplate.query(query, rowMapper, user_id);
        }
        else {
            String query = "select * from Quiz where user_id = ? and cat_id = ?";
            return jdbcTemplate.query(query, rowMapper, user_id, filterCategoryID);
        }
    }

}
