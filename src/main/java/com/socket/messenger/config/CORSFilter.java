package com.socket.messenger.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpServletRequest = (HttpServletRequest) request;
        var httpservletResponse = (HttpServletResponse) response;

        httpservletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://172.20.10.6:3000");
        httpservletResponse.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        httpservletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpservletResponse.setHeader("Access-Control-Allow-Methods","*");
        if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
            httpservletResponse.setStatus(HttpServletResponse.SC_OK);
        }else {
            chain.doFilter(httpServletRequest, httpservletResponse);
        }
    }

}
