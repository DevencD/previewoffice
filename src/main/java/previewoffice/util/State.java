package previewoffice.util;

public enum State {
    
    ENABLE("启用",1),
    DISABLE("禁用",0);
    
    private String alias;
    private int value;
    
 // 成员变量  

    // 构造方法  
    private State(String alias, int value) {  
        this.alias = alias;  
        this.value = value;  
    }  
    // 普通方法  
    public static String getAlias(int value) {  
        for (State c : State.values()) {  
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
