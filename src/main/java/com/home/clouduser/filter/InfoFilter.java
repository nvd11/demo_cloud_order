package com.home.clouduser.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebFilter
@Order(1)
@Slf4j
public class InfoFilter implements Filter {
    @Autowired
    private String hostname;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Log the request information
        log.info("API of hostname: {} has been called, Request Method: {}, Request URI: {}, Requested from : {}",  hostname, httpRequest.getMethod(), httpRequest.getRequestURI(), httpRequest.getRemoteAddr());
        // You can log more information as needed

        // Call the next filter in the chain
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
        // Cleanup code
    }
}
