package previewoffice.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;

import previewoffice.mapper.IDataDictionaryMapper;
import previewoffice.service.IDDService;
import previewoffice.vo.DataDictionaryVO;


@Controller
public class DataDictionaryController
{

    @Autowired
    IDataDictionaryMapper dataDictionaryMapper;

    @Autowired
    IDDService ddService;

    @RequestMapping(method = RequestMethod.POST, value = "/getDDByKey")
    @ResponseBody
    public Object getDDByKey(HttpServletRequest httpServletRequest)
    {
        String key = httpServletRequest.getParameter("DDKey");
        return ddService.getDDVOByKey(key);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getDDByID")
    @ResponseBody
    public Object getDDByID(HttpServletRequest httpServletRequest)
    {
        String id = httpServletRequest.getParameter("DDID");
        return dataDictionaryMapper.getDDByID(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDDValueByKey")
    @ResponseBody
    public Object getDDValueByKey(HttpServletRequest httpServletRequest)
    {
        String key = httpServletRequest.getParameter("DDKey");
        return ddService.getDDValueByKey(key);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getValueByKey")
    @ResponseBody
    public Map<String, String> getValueByKey(HttpServletRequest httpServletRequest)
    {
        Map<String, String> result = new HashMap<String, String>();
        String key = httpServletRequest.getParameter("key");
        String value = ddService.getDDValueByKey(key);
        result.put("value", value);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getDDList")
    @ResponseBody
    public Map<String, List<DataDictionaryVO>> getDDList()
    {
        Map<String, List<DataDictionaryVO>> result = new HashMap<String, List<DataDictionaryVO>>();
        result.put("result", dataDictionaryMapper.getDDList());
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateDDVOByID")
    @ResponseBody
    public void updateDDVOByID(HttpServletRequest httpServletRequest)
    {
        String ddID = httpServletRequest.getParameter("ddID");
        String ddKey = httpServletRequest.getParameter("ddKey");
        String ddValue = httpServletRequest.getParameter("ddValue");
        String ddDescription = httpServletRequest.getParameter("ddDescription");
        DataDictionaryVO dataDictionaryVO = new DataDictionaryVO();
        dataDictionaryVO.setId(ddID);
        dataDictionaryVO.setKey(ddKey);
        dataDictionaryVO.setValue(ddValue);
        dataDictionaryVO.setDescription(ddDescription);
        dataDictionaryMapper.updateDD(dataDictionaryVO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteDDVOByID")
    @ResponseBody
    public void deleteDDVOByID(HttpServletRequest httpServletRequest)
    {
        String ddID = httpServletRequest.getParameter("DDID");
        dataDictionaryMapper.deleteDDByID(ddID);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/savedd")
    @ResponseBody
    public void createDD(HttpServletRequest httpServletRequest)
    {
        String ddID = httpServletRequest.getParameter("ddID");
        String ddKey = httpServletRequest.getParameter("ddKey");
        String ddValue = httpServletRequest.getParameter("ddValue");
        String ddDescription = httpServletRequest.getParameter("ddDescription");
        DataDictionaryVO dataDictionaryVO = new DataDictionaryVO();
        dataDictionaryVO.setId(ddID);
        dataDictionaryVO.setKey(ddKey);
        dataDictionaryVO.setValue(ddValue);
        dataDictionaryVO.setDescription(ddDescription);
        if (StringUtils.isEmpty(ddID))
        {
            ddService.createDD(dataDictionaryVO);
        }
        else
        {
            dataDictionaryMapper.updateDD(dataDictionaryVO);
        }

    }

}
