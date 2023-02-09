package quiz_system.dao;

import quiz_system.domain.FeedBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/5/23 3:19 AM
 */
@Repository
public class FeedBackDao {
    private JdbcTemplate jdbcTemplate;
    private FeedBackRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public FeedBackDao(JdbcTemplate jdbcTemplate, FeedBackRowMapper rowMapper,
                       NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<FeedBack> getAllFeedBack() {
        String query = "select * from FeedBack";
        return jdbcTemplate.query(query, rowMapper);
    }

    public void createNewFeedBack(Integer rating, String message) {
        String query = "insert into FeedBack (rating, description) values(?, ?)";
        jdbcTemplate.update(query, rating, message);
    }


}