package mybatis.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FileUploadConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry.addResourceHandler("/images/**").addResourceLocations("C:\\Users\\Oudom\\Pictures\\pic_upload\\");
        registry.addResourceHandler("/image/**").addResourceLocations("file:///"+System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\");
    }
}
