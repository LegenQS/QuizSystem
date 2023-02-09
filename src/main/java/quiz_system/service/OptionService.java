package quiz_system.service;

import quiz_system.dao.OptionDao;
import quiz_system.domain.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/5/23 12:43 AM
 */
@Service
public class OptionService {
    private final OptionDao optionDao;

    @Autowired
    public OptionService(OptionDao optionDao) {this.optionDao = optionDao; }

    public void insertOption(Integer question_id, String description, boolean is_correct) {
        optionDao.insertOption(question_id, description, is_correct);
    }

    public List<List<Option>> getOptionByQuestionIDs(List<Integer> ids) {
        return optionDao.getOptionByQuestionIDs(ids);
    }

    public List<Option> getOptionByQuestionID(int id) {
        return optionDao.getOptionByQuestionID(id);
    }

    public Option getOptionByID(int id) {
        return optionDao.getOptionByID(id);
    }

    public List<Option> getOptionByIDs(List<Integer> ids) {
        return optionDao.getOptionByIDs(ids);
    }

    public void updateOptionByID(int option_id, String description, boolean is_correct) {
        optionDao.updateOptionByID(option_id, description, is_correct);
    }
}