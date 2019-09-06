package previewoffice.util;

public enum OperType {

    CREATE("创建",1),
    DELETE("删除",2),
    MODIFY("修改",3),
    DOWNLOAD("下载",4),
    PREVIEW("预览",4);
    
    
    private String alias;
    private int value;
    
 // 成员变量  

    // 构造方法  
    private OperType(String alias, int value) {  
        this.alias = alias;  
        this.value = value;  
    }  
    // 普通方法  
    public static String getAlias(int value) {  
        for (OperType c : OperType.values()) {  
            if (c.getValue() == value) {  
                return c.alias;  
            }  
        }  
        return null;  
    }  
    // get set 方法  
    public String getAlias() {  
        return alias;  
    }  
    public void setName(String alias) {  
        this.alias = alias;  
    }  
    public int getValue() {  
        return value;  
    }  
    public void setValue(int value) {  
        this.value = value;  
    }
}
