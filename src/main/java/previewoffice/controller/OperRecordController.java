package previewoffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import previewoffice.service.impl.OperRecordServiceImpl;
import previewoffice.vo.OperRecordVO;

@Controller
public class OperRecordController
{
    @Autowired
    private OperRecordServiceImpl operRecord;
    
    @RequestMapping(method = RequestMethod.GET, value = "/getOperRecordList")
    @ResponseBody
    public Map<String, List<OperRecordVO>> getDDList()
    {
        Map<String, List<OperRecordVO>> result = new HashMap<String, List<OperRecordVO>>();
        result.put("result", operRecord.queryAllOperRecord());
        return result;
    }

}
