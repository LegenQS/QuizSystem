package quiz_system.service;

import quiz_system.dao.CategoryDao;
import quiz_system.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/5/23 11:16 PM
 */
@Service
public class CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {this.categoryDao = categoryDao; }

    public List<Category> getCategory() {
        return categoryDao.getAllCategory();
    }

    public Category getCategoryByName(String cat_name) {
        return categoryDao.getCategoryByName(cat_name);
    }
}