package quiz_system.dao;

import quiz_system.domain.Option;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eric Wang
 * @date 2/4/23 4:57 PM
 */
@Component
public class OptionRowMapper implements RowMapper<Option> {
    @Override
    public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
        Option option = new Option();
        option.setOption_id(rs.getInt("option_id"));
        option.setQuestion_id(rs.getInt("question_id"));
        option.setDescription(rs.getString("description"));
        option.set_correct(rs.getBoolean("is_correct"));
        return option;
    }
}