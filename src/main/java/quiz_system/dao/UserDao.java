package quiz_system.dao;

import quiz_system.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private UserRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper,
                   NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createNewUser(User user) {
        String query = "insert into User (username, first_name, last_name" +
                ", address, email, password, phone, is_admin, is_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                user.getUsername(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getAddress(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.is_admin(),
                user.is_active()
                );
    }

    public User getUserByName (String username){
        String query = "select * from User where username = ?";
        List<User> user =  jdbcTemplate.query(query, rowMapper, username);
        return user.size() == 0 ? null : user.get(0);
    }

    public List<User> getUserByIDs (List<Integer> user_ids){
        String query = "SELECT * FROM User WHERE user_id IN (:user_ids)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("user_ids", user_ids);
        return namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);
    }

    public List<User> getAllUsers() {
        String query = "select * from User";
        return jdbcTemplate.query(query, rowMapper);
    }

    public void updateUserStatus(int user_id) {
        String query = "update User " +
                "set is_active = if(is_active, 0, 1) " +
                "where user_id = ?";
        jdbcTemplate.update(query, user_id);
    }
}
