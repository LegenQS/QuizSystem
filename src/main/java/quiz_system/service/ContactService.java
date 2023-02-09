package quiz_system.service;

/**
 * @author Eric Wang
 * @date 2/5/23 2:40 AM
 */

import quiz_system.dao.ContactDao;
import quiz_system.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactService {
    private final ContactDao contactDao;

    @Autowired
    public ContactService(ContactDao contactDao) {this.contactDao = contactDao; }

    public List<Contact> getAllContact() {
        return contactDao.getAllContact();
    }

    public void createNewContact(Integer user_id, String message, long time) {
        contactDao.createNewContact(user_id, message, time);
    }
}
