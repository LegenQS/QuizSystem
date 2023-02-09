package quiz_system.dao;

import quiz_system.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Eric Wang
 * @date 2/4/23 12:28 AM
 */
@Repository
public class ContactDao {
    private JdbcTemplate jdbcTemplate;
    private ContactRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ContactDao(JdbcTemplate jdbcTemplate, ContactRowMapper rowMapper,
                   NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public List<Contact> getAllContact() {
        String query = "select * from Contact";
        return jdbcTemplate.query(query, rowMapper);
    }

    public void createNewContact(Integer user_id, String message, long time) {
        Timestamp timestamp = new Timestamp(time);
        String query = "insert into Contact (user_id, message, time) values(?, ?, ?)";
        jdbcTemplate.update(query, user_id, message, timestamp);
    }
}