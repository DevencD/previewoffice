package previewoffice.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Index
{

    @RequestMapping("/index")
    public String index()
    {
        return "index";
    }
    
    @RequestMapping("/officeIndex")
    public String office2pdf()
    {
        return "office2pdf";
    }

    @RequestMapping("/preview")
    public String preview()
    {
        return "preview";
    }

    @RequestMapping("/upload")
    public String upload()
    {
        return "upload";
    }
    
    @RequestMapping("/ddlist")
    public String ddlist()
    {
        return "dataDictionary";
    }

}
