package previewoffice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import previewoffice.vo.LoginNameVO;

public interface ILoginNameWhiteListMapper
{
    void createByName(@Param("loginName") LoginNameVO loginName);
    
    void createLoginName(@Param("loginName") LoginNameVO loginName);
    
    void createByYzjAccount(@Param("loginAccount") LoginNameVO loginAccount);
    
    void confirmLoginName(@Param("login") LoginNameVO login);
    
    List<String> getAllLoginNameWhiteList();
    
    String getLoginNameByLoginIP(@Param("loginIP") String loginIP);
    
    int isExistLoginIPWhiteList(@Param("loginIP") String loginIP);
    
    int isExistLoginNameWhiteList(@Param("loginName") String loginName);
    
    int isExistLoginNameConfirm(@Param("loginName") String loginName);
    
    void deleteLoginName(@Param("id") String id);

}
