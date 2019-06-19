package mybatis.configuration;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan("mybatis.repository.ArticleRepository")
@MapperScan("mybatis.repository.CategoryRepository")
public class MybatisConfiguration {
    @Autowired
    DataSource dataSource;

    @Bean
    SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean obj = new SqlSessionFactoryBean();
        obj.setDataSource(dataSource);
        return obj;
    }
    @Bean
    DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }
}
