package quiz_system.dao;

import quiz_system.domain.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eric Wang
 * @date 2/4/23 12:35 AM
 */
@Component
public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setContact_id(rs.getInt("contact_id"));
        contact.setUser_id(rs.getInt("user_id"));
        contact.setMessage(rs.getString("message"));
        contact.setTime(rs.getTimestamp("time"));

        return contact;
    }
}
