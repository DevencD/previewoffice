package previewoffice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import previewoffice.mapper.IDataDictionaryMapper;
import previewoffice.service.IDDService;

@Controller
public class DataDictionaryController
{

    @Autowired
    IDataDictionaryMapper dataDictionaryMapper;
    
    @Autowired
    IDDService ddService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/getDDByKey")
    @ResponseBody
    public Object getDDByKey(HttpServletRequest httpServletRequest) {
        String key = httpServletRequest.getParameter("DDKey");
        return ddService.getDDVOByKey(key);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/getDDByID")
    @ResponseBody
    public Object getDDByID(HttpServletRequest httpServletRequest) {
        String id = httpServletRequest.getParameter("DDID");
        return dataDictionaryMapper.getDDByID(Integer.parseInt(id));
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/getDDValueByKey")
    @ResponseBody
    public Object getDDValueByKey(HttpServletRequest httpServletRequest) {
        String key = httpServletRequest.getParameter("DDKey");
        return ddService.getDDValueByKey(key);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/getValueByKey")
    @ResponseBody
    public Map<String,Integer> getValueByKey(HttpServletRequest httpServletRequest) {
        Map<String,Integer> result = new HashMap<String,Integer>();
        String key = httpServletRequest.getParameter("key");
        String value = ddService.getDDValueByKey(key);
        result.put("value", Integer.parseInt(value));
        return result;
    }
    
    
}
