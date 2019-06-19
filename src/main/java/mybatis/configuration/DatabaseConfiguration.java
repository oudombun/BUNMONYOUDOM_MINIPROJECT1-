package mybatis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    @Profile("db_local")
    public DataSource dataSource(){
        DriverManagerDataSource db = new DriverManagerDataSource();
        db.setDriverClassName("org.postgresql.Driver");
        db.setUrl("jdbc:postgresql://localhost:5432/sp_miniproject");
        db.setUsername("postgres");
        db.setPassword("oudom");
        return db;
    }
    @Bean
    @Profile("db_h2")
    public DataSource memory(){
        EmbeddedDatabaseBuilder builder=new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        builder.addScripts("sql/table.sql","sql/data.sql");
        return builder.build();
    }
}
