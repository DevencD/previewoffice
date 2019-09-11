package previewoffice.vo;

import java.util.Date;

public class OperRecordVO
{
    private String id;
    
    private String operUser;
    
    private String operIP;
    
    private Date operTime;
    
    private int operType;
    
    private String operTypeDesc;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getOperUser()
    {
        return operUser;
    }

    public void setOperUser(String operUser)
    {
        this.operUser = operUser;
    }

    public String getOperIP()
    {
        return operIP;
    }

    public void setOperIP(String operIP)
    {
        this.operIP = operIP;
    }

    public Date getOperTime()
    {
        return operTime;
    }

    public void setOperTime(Date operTime)
    {
        this.operTime = operTime;
    }

    public int getOperType()
    {
        return operType;
    }

    public void setOperType(int operType)
    {
        this.operType = operType;
    }

    public String getOperTypeDesc()
    {
        return operTypeDesc;
    }

    public void setOperTypeDesc(String operTypeDesc)
    {
        this.operTypeDesc = operTypeDesc;
    }
    
    

}
