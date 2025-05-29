package com.javaweb.training_center_lms.filter;

import com.javaweb.training_center_lms.controllers.BaseController;
import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.utils.SessionUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthorizationFilter implements Filter {

    private final SessionUtil sessionUtil = SessionUtil.getInstance();
    private ServletContext context;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();

        if(url.startsWith("/controllers/manager")){
            // Check if the user is authenticated
            Account account = (Account) sessionUtil.getValue("account", request);
            if(account != null){
                if(account.getRole().equalsIgnoreCase("manager")){
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                else{
                    BaseController.responseRedirectTo("/controllers/LoginController/login?action=login", request, response);
                }
            }
            else {
                BaseController.responseRedirectTo("/controllers/LoginController/login?action=login", request, response);
            }
        }
        else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
