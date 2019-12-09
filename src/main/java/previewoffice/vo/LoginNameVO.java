package previewoffice.vo;

import java.util.ArrayList;
import java.util.List;

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
    
    public static void main(String[] args)
    {
        List<String> a = new ArrayList<String>();
        
        for(int i = 1;i<35;i++) {
            a.add("" + i);
        }
        System.out.println(a);
        double perSize = 10;
        double totalSize = a.size();
        int pages = (int) Math.ceil(totalSize / perSize);
        List<String> tempSqlArr = new ArrayList<String>();
        StringBuffer sqlUnion = new StringBuffer();
        for(int i =0;i<pages;i++){
            int fromIndex = i * (int)perSize;
            int toIndex = (i+1) * (int)perSize;
            toIndex = toIndex > (int)totalSize ? (int)totalSize : toIndex;
            tempSqlArr = a.subList(fromIndex, toIndex);
            for(String builder : tempSqlArr){
                sqlUnion.append(" union ").append(builder.toString());
            }
            System.out.println(sqlUnion);
            sqlUnion.setLength(0);;
        }
    }
}
