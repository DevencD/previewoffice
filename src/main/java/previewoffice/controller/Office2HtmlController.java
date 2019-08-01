package previewoffice.controller;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import previewoffice.mapper.IAttachmentMapper;
import previewoffice.service.IDDService;
import previewoffice.util.Office2PDF;
import previewoffice.util.POIReadExcel;
import previewoffice.util.ProjectConstant;
import previewoffice.vo.AttachmentVO;


@Controller
public class Office2HtmlController
{
    @Autowired
    private IDDService ddService;
    
    @Autowired
    private IAttachmentMapper attDao;

    @RequestMapping(value = "/office2html")
    @ResponseBody
    public Map<String, String> office2Html(HttpServletRequest httpServletRequest)
    {
        String fileID = httpServletRequest.getParameter("fileID");
        AttachmentVO file = attDao.getFileById(fileID);
        String filePath = file.getFilePath();
        String html = POIReadExcel.excelWriteToHtml(filePath);
        Map<String, String> result = new HashMap<String, String>();
        result.put("state", "success");
        result.put("html", html);
        return result;
    }

    @RequestMapping(value = "/office2pdf")
    @ResponseBody
    public Map<String, String> office2PDF(HttpServletRequest httpServletRequest)
    {
        String fileID = httpServletRequest.getParameter("fileID");
        AttachmentVO file = attDao.getFileById(fileID);
        String filePath = file.getFilePath();
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1,
            filePath.lastIndexOf('.')) + ".pdf";
        String pdfPath = ProjectConstant.PDFPATH + fileName;
        Map<String, String> openofficeConfig = new HashMap<String, String>();
        String openofficeIP = ddService.getDDValueByKey("openofficeIP");
        String openofficePort = ddService.getDDValueByKey("openofficePort");
        openofficeConfig.put("openofficeIP", openofficeIP);
        openofficeConfig.put("openofficePort", openofficePort);
        boolean state = Office2PDF.office2pdf(openofficeConfig, filePath, pdfPath);
        Map<String, String> result = new HashMap<String, String>();
        result.put("state", String.valueOf(state));
        result.put("file", fileName);
        return result;
    }


}
