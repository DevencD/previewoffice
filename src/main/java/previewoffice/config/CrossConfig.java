package previewoffice.config;


import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import previewoffice.util.ProjectConstant;


@Configuration
public class CrossConfig extends WebMvcConfigurerAdapter
{

    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        File path = null;
        try
        {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String pdfPath = path.getParentFile().getParentFile().getParent() + File.separator
                         + "logistics" + File.separator + "pdf" + File.separator;
        String filePath = path.getParentFile().getParentFile().getParent() + File.separator
                          + "logistics" + File.separator + "file";
        ProjectConstant.PDFPATH = pdfPath.replace("file:", "");
        ProjectConstant.SAVEFILEPATH = filePath.replace("file:", "");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/PDFViewer/**").addResourceLocations("classpath:/PDFViewer/");
        registry.addResourceHandler("/previewpdf/**").addResourceLocations(
            pdfPath.contains("file:") ? pdfPath : "file:" + pdfPath);
        super.addResourceHandlers(registry);
    }
}
