package mybatis.service.ArticleService;

import mybatis.repository.model.Article;
import mybatis.repository.model.ArticleFilter;
import mybatis.utility.Paging;

import java.util.List;

public interface ArticleService {
    void add(Article article);
    void update(Article article);
    void delete(int id);
    List<Article> findAll();
    Article findById(int id);
    int getLastId();
    List<Article> findAllFilter(ArticleFilter articleFilter, Paging paging);
    public Integer countAllArticles(ArticleFilter articleFilter);






//    List<Integer> pageInfo(int page , int limit);
//    List<Article> paginateArticleFilter(ArticleFilter articleFilter,int page,int limit);
//    List<Integer> pageInfoFilter(ArticleFilter articleFilter,int page , int limit);
//    List<Article> paginateArticle(int page,int limit);
}
