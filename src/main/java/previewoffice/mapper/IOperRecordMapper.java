package previewoffice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import previewoffice.vo.OperRecordVO;

public interface IOperRecordMapper
{
    void createOperRecordByVO(@Param("operRecord") OperRecordVO operRecord);
    
    List<OperRecordVO> queryAll();
}
