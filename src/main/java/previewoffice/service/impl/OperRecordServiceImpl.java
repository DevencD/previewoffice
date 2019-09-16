package previewoffice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import previewoffice.mapper.ILoginNameWhiteListMapper;
import previewoffice.mapper.IOperRecordMapper;
import previewoffice.service.IOperRecordService;
import previewoffice.util.IpUtil;
import previewoffice.util.OperType;
import previewoffice.util.ProjectConstant;
import previewoffice.vo.OperRecordVO;

@Service
public class OperRecordServiceImpl implements IOperRecordService
{
    @Autowired
    private IOperRecordMapper operRecordDao;
    
    @Autowired
    private ILoginNameWhiteListMapper loginNameWhiteList;

    @Override
    public void recordOper(HttpServletRequest request, int operType)
    {
        OperRecordVO record = new OperRecordVO();
        record.setId(UUID.randomUUID().toString().replace("-", "")
            + ProjectConstant.OPERRECORDID_SUFFIX);
        String loginIP = IpUtil.getIpAddr(request);
        record.setOperIP(loginIP);
        record.setOperType(operType);
        record.setOperTime(new Date());
        String operUser = loginNameWhiteList.getLoginNameByLoginIP(loginIP);
        record.setOperUser(operUser);
        record.setOperTypeDesc(OperType.getAlias(operType));
        operRecordDao.createOperRecordByVO(record);

    }

    @Override
    public List<OperRecordVO> queryAllOperRecord()
    {
        List<OperRecordVO> result = operRecordDao.queryAll();
        for(OperRecordVO operRecord : result) {
            int operType = operRecord.getOperType();
            String operTypeDesc = OperType.getAlias(operType);
            operRecord.setOperTypeDesc(operTypeDesc);
        }
        return result;
    }

}
