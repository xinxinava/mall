package com.littlebean.mall.filter;

import com.littlebean.mall.common.ApiRestResponse;
import com.littlebean.mall.common.Constant;
import com.littlebean.mall.exception.MallExceptionEnum;
import com.littlebean.mall.model.pojo.Category;
import com.littlebean.mall.model.pojo.User;
import com.littlebean.mall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class AdminFilter implements Filter {

    @Autowired
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpSession session = request1.getSession();
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);

        if(currentUser == null){
            PrintWriter out=new HttpServletResponseWrapper(
                    (HttpServletResponse) response).getWriter();
            out.write("{\n"
                    + "    \"status\": 10007,\n"
                    + "    \"msg\": \"NEED_LOGIN\",\n"
                    + "    \"data\": null\n"
                    + "}");
            out.flush();
            out.close();
            return;
        }
        boolean adminRole = userService.checkAdminRole(currentUser);
        if(adminRole){
            chain.doFilter(request, response);
        }else {
            PrintWriter out = new HttpServletResponseWrapper(
                    (HttpServletResponse) response).getWriter();
            out.write("{\n"
                    + "    \"status\": 10009,\n"
                    + "    \"msg\": \"NEED_ADMIN\",\n"
                    + "    \"data\": null\n"
                    + "}");
            out.flush();
            out.close();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
