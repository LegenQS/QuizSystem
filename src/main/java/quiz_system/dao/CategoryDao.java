package quiz_system.dao;

import quiz_system.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/4/23 5:14 PM
 */
@Repository
public class CategoryDao {
    private JdbcTemplate jdbcTemplate;
    private CategoryRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CategoryDao(JdbcTemplate jdbcTemplate, CategoryRowMapper rowMapper,
                      NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Category> getAllCategory() {
        String query = "select * from Category";
        return jdbcTemplate.query(query, rowMapper);
    }

    public Category getCategoryByName(String cat_name) {
        String query = "select * from Category where cat_name = ?";
        List<Category> res = jdbcTemplate.query(query, rowMapper, cat_name);
        return res.size() == 0 ? null : res.get(0);
    }
}
