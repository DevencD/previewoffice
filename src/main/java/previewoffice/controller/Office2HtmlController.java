package previewoffice.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import previewoffice.util.Office2PDF;
import previewoffice.util.POIReadExcel;
import previewoffice.util.ProjectConstant;

@Controller
public class Office2HtmlController
{
    @RequestMapping(method = RequestMethod.POST, value = "/office2html")
    @ResponseBody
    public Map<String, String> office2Html(HttpServletRequest httpServletRequest) {
        String filePath = httpServletRequest.getParameter("filePath");
        String html = POIReadExcel.excelWriteToHtml(filePath);
        Map<String, String> result = new HashMap<String, String>();
        result.put("state", "success");
        result.put("html", html);
        return result;
    }
    
    @RequestMapping(value = "/office2pdf")
    @ResponseBody
    public Map<String,String> office2PDF(HttpServletRequest httpServletRequest) {
        String filePath = httpServletRequest.getParameter("filePath");
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator)+1, filePath.lastIndexOf('.'))+".pdf";
        String pdfPath = ProjectConstant.PDFPATH + fileName;
        boolean state = Office2PDF.office2pdf(filePath, pdfPath);
        Map<String, String> result = new HashMap<String, String>();
        result.put("state", String.valueOf(state));
        result.put("file", fileName);
        return result;
    }
    
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/preview")
    public String preview() {
        return "preview";
    }

}
