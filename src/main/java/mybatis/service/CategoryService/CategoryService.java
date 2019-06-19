package mybatis.service.CategoryService;

import mybatis.repository.model.ArticleFilter;
import mybatis.repository.model.Category;
import mybatis.utility.Paging;

import java.util.List;

public interface CategoryService {
    void add(Category article);
    void update(Category article);
    void delete(int id);
    List<Category> findAll();
    Category findById(int id);
    int getLastId();
    List<Category> findAllFilter(Paging paging);
    public Integer countAllCate();
}
