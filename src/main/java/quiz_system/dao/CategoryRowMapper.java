package quiz_system.dao;

import quiz_system.domain.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eric Wang
 * @date 2/4/23 5:15 PM
 */
@Component
public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setCat_id(rs.getInt("cat_id"));
        category.setCat_name(rs.getString("cat_name"));
        return category;
    }
}