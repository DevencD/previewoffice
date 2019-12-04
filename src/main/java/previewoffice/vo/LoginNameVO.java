package previewoffice.vo;

public class LoginNameVO
{
    
    private String id;
    
    private String name;
    
    private String yzjAccount;
    
    private int isInnerNet;
    
    private String ipAddress;
    
    private int state;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getYzjAccount()
    {
        return yzjAccount;
    }

    public void setYzjAccount(String yzjAccount)
    {
        this.yzjAccount = yzjAccount;
    }

    public int getIsInnerNet()
    {
        return isInnerNet;
    }

    public void setIsInnerNet(int isInnerNet)
    {
        this.isInnerNet = isInnerNet;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }
    
    public int getState()
    {
        return state;
    }
    public void setState(int state)
    {
        this.state = state;
    }

}
