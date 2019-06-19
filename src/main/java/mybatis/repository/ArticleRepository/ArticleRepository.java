package mybatis.repository.ArticleRepository;

import mybatis.repository.model.Article;
import mybatis.repository.model.ArticleFilter;
import mybatis.repository.provider.ArticleProvider;
import mybatis.utility.Paging;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository {
    @Insert("insert into tbl_articles (title,description,author,thumbnail,cate_id) values(#{title},#{desc},#{author},#{thumbnail},#{category.id})")
    void add(Article article);

    @Update("update tbl_articles set title = #{title},description = #{desc},author = #{author},thumbnail = #{thumbnail},cate_id = #{category.id} where id = #{id}")
    void update(Article article);

    @Delete("delete from tbl_articles where id = #{id}")
    void delete(int id);

    @Select("select a.*,c.cate_name catename from tbl_articles a inner join tbl_categories c on a.cate_id=c.id order by a.id asc")
    @Results({
            @Result(property = "desc",column = "description"),
            @Result(property = "category.id",column = "cate_id"),
            @Result(property = "category.name",column = "catename")
    })
    List<Article> findAll();


    @Select("select a.*,c.cate_name catename from tbl_articles a inner join tbl_categories c on a.cate_id=c.id where a.id=#{id}")
    @Results({
            @Result(property = "desc",column = "description"),
            @Result(property = "category.id",column = "cate_id"),
            @Result(property = "category.name",column = "catename")
    })
    Article findById(int id);

    @Select("select ifnull((select id from tbl_articles order by id desc limit 1),1)")
    int getLastId();



    @SelectProvider(method = "findAllFilter",type = ArticleProvider.class)
    @Results({
            @Result(property = "desc",column = "description"),
            @Result(property = "category.id",column = "cate_id"),
            @Result(property = "category.name",column = "catename")
    })
    List<Article> findAllFilter(@Param("filter") ArticleFilter articleFilter,@Param("paging") Paging paging);

    @SelectProvider(method = "countAllArticles",type = ArticleProvider.class)
    public Integer countAllArticles(ArticleFilter articleFilter);

}
