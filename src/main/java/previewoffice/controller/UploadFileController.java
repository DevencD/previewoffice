package previewoffice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import previewoffice.util.UploadActionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
 
@RestController
@RequestMapping("/uploadFile")
public class UploadFileController {
 
    @PostMapping("/upload")
    public String upload(HttpServletRequest httpServletRequest) throws Exception {
        List<String> list = UploadActionUtil.uploadFile(httpServletRequest);
        return list.get(0).toString();
    }
}
