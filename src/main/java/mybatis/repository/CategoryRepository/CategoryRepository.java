package mybatis.repository.CategoryRepository;

import mybatis.repository.model.Category;
import mybatis.repository.provider.CategoryProvider;
import mybatis.utility.Paging;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {
    @Insert("insert into tbl_categories (cate_name) values(#{name})")
    void add(Category category);

    @Update("update tbl_categories set cate_name = #{name} where id = #{id}")
    void update(Category category);

    @Delete("delete from tbl_categories where id = #{id}")
    void delete(int id);

    @Select("select * from tbl_categories order by id asc")
    @Results({
            @Result(property = "name",column = "cate_name")
    })
    List<Category> findAll();

    @Select("select * from tbl_categories where id = #{id}  order by id asc")
    @Results({
            @Result(property = "name",column = "cate_name")
    })
    Category findById(int id);

    @Select("select ifnull((select id from tbl_categories order by id desc limit 1),1)")
    int getLastId();

    @SelectProvider(method = "findAllFilter",type = CategoryProvider.class)
    @Results({
            @Result(property = "name",column = "cate_name")
    })
    List<Category> findAllFilter(Paging paging);

    @SelectProvider(method = "countAllCate",type = CategoryProvider.class)
    public Integer countAllCate();
}
