package previewoffice.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import previewoffice.vo.AttachmentVO;


@Mapper
@Repository
public interface IAttachmentMapper
{
    void createAttachmentByVO(@Param("file") AttachmentVO file);
    
    void createAttachment(@Param("id") String fileID, @Param("fileName") String fileName, @Param("state") int state,@Param("fileCreateTime")Date fileCreateTime);

    void updateComplete(@Param("fileID") String fileID,
                        @Param("fileSeriaLength") long fileSeriaLength,
                        @Param("filePath") String filePath,
                        @Param("fileCompleteTime")Date fileCompleteTime);

    List<AttachmentVO> queryAll();

    String getFileNameById(@Param("id") String fileID);

    AttachmentVO getFileById(@Param("id") String fileID);

    void deleteAttachmentById(@Param("id") String fileID);
}
