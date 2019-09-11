package previewoffice.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import previewoffice.mapper.IAttachementPartMapper;
import previewoffice.service.IAttPartService;
import previewoffice.vo.AttachmentPartVO;

@Service
public class AttPartServiceImpl implements IAttPartService
{
    
    @Autowired
    private IAttachementPartMapper attPartMapper;

    @Override
    public void createAttachmentPart(AttachmentPartVO attachmentPart)
    {
        attachmentPart.setFileCreateTime(new Date());
       attPartMapper.createAttachmentPart(attachmentPart);

    }

}
