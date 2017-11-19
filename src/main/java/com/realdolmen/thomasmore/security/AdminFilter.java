package com.realdolmen.thomasmore.security;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.service.UserService;
import com.realdolmen.thomasmore.session.UserSession;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class AdminFilter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        User loginAdmin = null;
        int level = 0;
        if (request.getSession().getAttribute("user")!= null){
            loginAdmin = (User) request.getSession().getAttribute("user");
            level = loginAdmin.getUserLevel();
        }
        String loginURL = request.getContextPath() + "/user/login.xhtml";
        if(loginAdmin != null && loginAdmin.getUserLevel() == 3){
            chain.doFilter(req, res);
        }
        else if (level != 3){
            response.sendRedirect(request.getContextPath() + "/user/noAccess.xhtml");
        }
        else{
            response.sendRedirect(loginURL);
        }
    }


    public void init(FilterConfig fConfig) throws ServletException {

    }

}
