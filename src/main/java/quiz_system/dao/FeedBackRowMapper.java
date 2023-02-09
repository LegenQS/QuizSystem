package quiz_system.dao;

import quiz_system.domain.FeedBack;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eric Wang
 * @date 2/5/23 3:20 AM
 */
@Component
public class FeedBackRowMapper implements RowMapper<FeedBack> {
    @Override
    public FeedBack mapRow(ResultSet rs, int rowNum) throws SQLException {
        FeedBack feedBack = new FeedBack();
        feedBack.setFeedback_id(rs.getInt("feedback_id"));
        feedBack.setRating(rs.getInt("rating"));
        feedBack.setDescription(rs.getString("description"));

        return feedBack;
    }
}