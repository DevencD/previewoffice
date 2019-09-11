package previewoffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import previewoffice.mapper.ILoginNameWhiteListMapper;
import previewoffice.util.IpUtil;
import previewoffice.util.ProjectConstant;
import previewoffice.vo.LoginNameVO;

@Controller
public class LoginNameWhiteListController
{
    @Autowired
    private ILoginNameWhiteListMapper loginWhiteList;
    
    @RequestMapping(method = RequestMethod.GET, value = "/getWhiteList")
    @ResponseBody
    public Map<String, List<String>> getLoginNameWhiteList(HttpServletRequest httpServletRequest)
    {
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        result.put("result", loginWhiteList.getAllLoginNameWhiteList());
        return result;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/saveLoginName")
    @ResponseBody
    public void createWLByName(HttpServletRequest httpServletRequest)
    {
        String loginName = httpServletRequest.getParameter("loginName");
        LoginNameVO loginNameVO = new LoginNameVO();
        loginNameVO.setName(loginName);
        loginNameVO.setId(UUID.randomUUID().toString().replace("-", "")
            + ProjectConstant.LOGINNAMEWHITELISTID_SUFFIX);
        loginWhiteList.createByName(loginNameVO);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/confirmLoginName")
    @ResponseBody
    public Map<String, String> confirmLoginName(HttpServletRequest httpServletRequest)
    {
        Map<String, String> result = new HashMap<String, String>();
        String loginName = httpServletRequest.getParameter("loginName");
        int isInnerNet = Integer.parseInt(httpServletRequest.getParameter("isInnerNet"));
        int isExistLoginName = loginWhiteList.isExistLoginNameWhiteList(loginName);
        if(isExistLoginName < 1) {
            result.put("message", "姓名或者云之家账号不正确，或者联系管理员添加！");
            result.put("result", Boolean.FALSE.toString());
        }else {
            result.put("result", Boolean.TRUE.toString());
            LoginNameVO login = new  LoginNameVO();
            String loginIp = IpUtil.getIpAddr(httpServletRequest);
            login.setIpAddress(loginIp);
            login.setIsInnerNet(isInnerNet);
            login.setName(loginName);
            int isExistLoginNameConfirm = loginWhiteList.isExistLoginNameConfirm(loginName);
            if(isExistLoginNameConfirm<1) {
                login.setId(UUID.randomUUID().toString().replace("-", "")
            + ProjectConstant.LOGINNAMEWHITELISTID_SUFFIX);
                loginWhiteList.createLoginName(login);
            }else {
                loginWhiteList.confirmLoginName(login);
            }
        }
        return result;
    }

}
