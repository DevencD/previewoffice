package previewoffice.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import previewoffice.vo.OperRecordVO;

public interface IOperRecordService
{
    public void recordOper(HttpServletRequest httpServletRequest,int OperType,String bizObjID);
    
    public List<OperRecordVO> queryAllOperRecord();

}
