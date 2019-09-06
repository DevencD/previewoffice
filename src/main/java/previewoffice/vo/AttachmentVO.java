package previewoffice.vo;


import java.util.Date;


public class AttachmentVO
{

    private String id;

    private String fileName;

    private long fileSeriaLength;

    private String filePath;

    private Date fileCreateTime;

    private Date fileCompleteTime;
    
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

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public long getFileSeriaLength()
    {
        return fileSeriaLength;
    }

    public void setFileSeriaLength(long fileSeriaLength)
    {
        this.fileSeriaLength = fileSeriaLength;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public Date getFileCreateTime()
    {
        return fileCreateTime;
    }

    public void setFileCreateTime(Date fileCreateTime)
    {
        this.fileCreateTime = fileCreateTime;
    }

    public Date getFileCompleteTime()
    {
        return fileCompleteTime;
    }

    public void setFileCompleteTime(Date fileCompleteTime)
    {
        this.fileCompleteTime = fileCompleteTime;
    }

}
