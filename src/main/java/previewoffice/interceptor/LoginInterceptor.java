package previewoffice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import previewoffice.mapper.ILoginNameWhiteListMapper;
import previewoffice.util.IpUtil;

@Component
public class LoginInterceptor implements HandlerInterceptor
{
    @Autowired
    private ILoginNameWhiteListMapper loginWhitelist;
    

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
        throws Exception
    {
        String uri = request.getRequestURI();
        String loginIP = IpUtil.getIpAddr(request);
        int isExistLoginIP = loginWhitelist.isExistLoginIPWhiteList(loginIP);
        if(uri.contains("loginconfirm") || uri.contains("confirmLoginName") || uri.contains("noPerm")) {
            if(isExistLoginIP < 1) {
                return true;
            }else {
                int isExistLoginIPOn = loginWhitelist.isExistLoginIPWhiteListOn(loginIP);
                if(isExistLoginIPOn > 0 && uri.contains("noPerm")) {
                    return true;
                } else {
                    response.sendRedirect("/index");
                    return false;
                }
            }
        }
        if(isExistLoginIP < 1) {
            response.sendRedirect("/loginconfirm");
            return false;
        }else {
            int isExistLoginIPOn = loginWhitelist.isExistLoginIPWhiteListOn(loginIP);
            if(isExistLoginIPOn > 0) {
                response.sendRedirect("/noPerm");
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView)
        throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex)
        throws Exception
    {

    }

}
