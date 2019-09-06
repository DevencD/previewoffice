package previewoffice.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import previewoffice.mapper.IOperRecordMapper;
import previewoffice.service.IOperRecordService;
import previewoffice.util.IpUtil;
import previewoffice.util.ProjectConstant;
import previewoffice.vo.OperRecordVO;

@Service
public class OperRecordServiceImpl implements IOperRecordService
{
    @Autowired
    private IOperRecordMapper operRecordDao;

    @Override
    public void recordOper(HttpServletRequest request, int operType)
    {
        OperRecordVO record = new OperRecordVO();
        record.setId(UUID.randomUUID().toString().replace("-", "")
            + ProjectConstant.OPERRECORDID_SUFFIX);
        record.setOperIP(IpUtil.getIpAddr(request));
        record.setOperType(operType);
        operRecordDao.createOperRecordByVO(record);

    }

    @Override
    public List<OperRecordVO> queryAllOperRecord()
    {
        return operRecordDao.queryAll();
    }

}
