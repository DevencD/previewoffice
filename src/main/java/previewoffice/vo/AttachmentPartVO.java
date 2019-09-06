package previewoffice.vo;


import java.util.Date;


/**
 * id varchar(60) primary key, file_id varchar(60), file_seria int, file_content_part nclob,
 * file_create_time timestamp
 * 
 * @author Administrator
 */
public class AttachmentPartVO
{
    private String id;

    private String fileId;

    private int fileSeria;

    private String fileContentPart;

    private Date fileCreateTime;
    
    private int state;

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getFileId()
    {
        return fileId;
    }

    public void setFileId(String fileId)
    {
        this.fileId = fileId;
    }

    public int getFileSeria()
    {
        return fileSeria;
    }

    public void setFileSeria(int fileSeria)
    {
        this.fileSeria = fileSeria;
    }

    public String getFileContentPart()
    {
        return fileContentPart;
    }

    public void setFileContentPart(String fileContentPart)
    {
        this.fileContentPart = fileContentPart;
    }

    public Date getFileCreateTime()
    {
        return fileCreateTime;
    }

    public void setFileCreateTime(Date fileCreateTime)
    {
        this.fileCreateTime = fileCreateTime;
    }

}
