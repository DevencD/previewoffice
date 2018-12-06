package previewoffice;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"previewoffice"})
@Configuration
public class Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);

    }
    
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("10240MB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("102400MB");
        return factory.createMultipartConfig();
    }


}
