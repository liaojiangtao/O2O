package com.gentel.interceptor.shopadmin;

import com.gentel.entity.PersonInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/*
 * 店家管理系统登录验证
 *
 * */
public class ShopLoginInterceptor extends HandlerInterceptorAdapter {

    /*
     * 做主要事情前拦截，即用户操作发生前，改写perHandle里的逻辑，进行拦截
     * */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //从session中取出用户信息表
//        Object userObj = request.getSession().getAttribute("user");
//        if (userObj != null) {
//            //如果用户信息不为空则将session中的用户信息转换成为PersionInfo实体类对象
//            PersonInfo user = (PersonInfo) userObj;
//            //做空值判断，确保userId不为空并且该账号的可用状态为1，并且用户类型为店家
//            if (user != null && user.getUserId() != null && user.getUserId() > 0 && user.getEnableStatus() == 1) {
//                //如果通过验证则返回true，拦截器fanhuitrue之后，用户接下来的操作得以正常执行
//                return true;
//            }
//        }
//
//        //如果不满足登录验证，则直接跳转到账号登录页面
//        PrintWriter out = response.getWriter();
//        out.println("<html>");
//        out.println("<script>");
//        out.println("windows.open('" + request.getContextPath() + "/local/login?usertype=2','_self')");
//        out.println("</script>");
//        out.println("</html>");
//
//        return false;
//    }
}
