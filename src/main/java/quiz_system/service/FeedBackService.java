package quiz_system.service;

import quiz_system.dao.FeedBackDao;
import quiz_system.domain.FeedBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/5/23 3:19 AM
 */
@Service
public class FeedBackService {
    private final FeedBackDao feedBackDao;

    @Autowired
    public FeedBackService(FeedBackDao feedBackDao) {this.feedBackDao = feedBackDao; }

    public List<FeedBack> getAllFeedBack() {
        return feedBackDao.getAllFeedBack();
    }

    public void createNewFeedBack(Integer rating, String message) {
        feedBackDao.createNewFeedBack(rating, message);
    }


}
