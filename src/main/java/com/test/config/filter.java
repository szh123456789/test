package com.test.config;

import com.test.service.tools.token.token_util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/"})
public class filter implements Filter {

    private token_util tu;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String token = request.getHeader("token");
//        if(tu.verify(token)){
//            ((HttpServletResponse) servletResponse).sendRedirect("/sindex");
//            filterChain.doFilter(servletRequest,servletResponse);
//        };
    }

    @Override
    public void destroy() {

    }
}
