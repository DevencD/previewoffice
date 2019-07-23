package previewoffice.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import previewoffice.vo.AttachmentPartVO;


@Mapper
@Repository
public interface IAttachementPartMapper
{
    void createAttachmentPart(@Param("attachmentPart") AttachmentPartVO attachmentPart);

    List<AttachmentPartVO> getAttachementByFileID(@Param("fileID") String fileID);

    void deleteAttachmentPartByFileId(@Param("fileID") String fileID);

}
