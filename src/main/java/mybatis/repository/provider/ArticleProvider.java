package mybatis.repository.provider;

import mybatis.repository.model.ArticleFilter;
import mybatis.utility.Paging;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ArticleProvider {
    public String findAllFilter(@Param("filter") ArticleFilter articleFilter, @Param("paging") Paging paging){
        return new SQL(){{
            SELECT("a.*,c.cate_name catename");
            FROM("tbl_articles a");
            INNER_JOIN("tbl_categories c on a.cate_id=c.id");
            if(articleFilter.getTitle()!=null)
            WHERE("a.title ILIKE '%'||#{filter.title}||'%'");
            if(articleFilter.getCate_id()!=null)
            WHERE("a.cate_id = #{filter.cate_id}");
            ORDER_BY("a.id ASC limit #{paging.limit} offset #{paging.offset}");
        }}.toString();
    }


    public String countAllArticles(ArticleFilter articleFilter){
        return new SQL(){{
            SELECT("COUNT(*)");
            FROM("tbl_articles");
            if(articleFilter.getTitle()!=null)
                WHERE("title ILIKE '%'||#{title}||'%'");
            if(articleFilter.getCate_id()!=null)
                WHERE("cate_id = #{cate_id}");
        }}.toString();
    }
}
