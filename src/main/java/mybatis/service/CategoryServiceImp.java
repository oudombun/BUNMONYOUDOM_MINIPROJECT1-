package mybatis.service;

import mybatis.repository.CategoryRepository.CategoryRepository;
import mybatis.repository.model.ArticleFilter;
import mybatis.repository.model.Category;
import mybatis.service.CategoryService.CategoryService;
import mybatis.utility.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public void add(Category category) {
        categoryRepository.add(category);
    }

    @Override
    public void update(Category category) {
        categoryRepository.update(category);
    }

    @Override
    public void delete(int id) {
        categoryRepository.delete(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public int getLastId() {
        return categoryRepository.getLastId();
    }

    @Override
    public List<Category> findAllFilter(Paging paging) {
        paging.setTotalCount(categoryRepository.countAllCate());
        return categoryRepository.findAllFilter(paging);
    }

    @Override
    public Integer countAllCate() {
        return categoryRepository.countAllCate();
    }
}
