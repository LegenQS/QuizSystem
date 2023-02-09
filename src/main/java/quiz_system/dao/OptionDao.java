package quiz_system.dao;

import quiz_system.domain.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Wang
 * @date 2/4/23 4:55 PM
 */
@Repository
public class OptionDao {
    private JdbcTemplate jdbcTemplate;
    private OptionRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OptionDao(JdbcTemplate jdbcTemplate, OptionRowMapper rowMapper,
                   NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Option> getOptionByQuestionID(Integer question_id) {
        String query = "select * from `Option` where question_id = ?";
        return jdbcTemplate.query(query, rowMapper, question_id);
    }

    public void insertOption(Integer question_id, String description, boolean is_correct) {
        String query = "insert into `Option` (question_id, description, is_correct) values (?, ?, ?)";
        jdbcTemplate.update(query,
                question_id,
                description,
                is_correct);
    }

    public List<List<Option>> getOptionByQuestionIDs(List<Integer> ids) {
        String query = "SELECT * FROM `Option` WHERE question_id = ?";
        List<List<Option>> res = new ArrayList<>();
        for (int id: ids) {
            res.add(jdbcTemplate.query(query, rowMapper, id));
        }

        return res;
    }

    public void updateOptionByID(int option_id, String description, boolean is_correct) {
        String query = "update `Option` " +
                       "set description = ?, is_correct = ? " +
                       "where option_id = ?";
        jdbcTemplate.update(query, description, is_correct, option_id);
    }

    public Option getOptionByID(int id) {
        String query = "SELECT * FROM `Option` WHERE option_id = ?";
        return jdbcTemplate.queryForObject(query, rowMapper, id, Option.class);
    }

    public List<Option> getOptionByIDs(List<Integer> ids) {
        String query = "SELECT * FROM `Option` WHERE option_id IN (:ids)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("ids", ids);

        return namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);
    }
}
