package mybatis.repository.provider;

import mybatis.utility.Paging;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class CategoryProvider {
    public String findAllFilter(Paging paging){
        return new SQL(){{
            SELECT("*");
            FROM("TBL_CATEGORIES");
            ORDER_BY("id ASC limit #{limit} offset #{offset}");
        }}.toString();
    }
    public String countAllCate(){
        return new SQL(){{
            SELECT("count(*)");
            FROM("TBL_CATEGORIES");
        }}.toString();
    }
}
