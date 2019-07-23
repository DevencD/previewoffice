package previewoffice.vo;


import java.util.Date;


public class AttachmentVO
{

    private String id;

    private String fileName;

    private int fileSeriaLength;

    private String filePath;

    private Date fileCreateTime;

    private Date fileCompleteTime;

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

    public int getFileSeriaLength()
    {
        return fileSeriaLength;
    }

    public void setFileSeriaLength(int fileSeriaLength)
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
