package com.example.c3s1pcCustomer.filter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader("authorization");

        if(authHeader==null || !authHeader.startsWith("Bearer") ){
            throw new ServletException("Token missing");
        }
        else{
            String token = authHeader.substring(7);
            Claims claims=Jwts.parser().setSigningKey("mysecretkey").parseClaimsJws(token).getBody();
            System.out.println("claims : " + claims);
            request.setAttribute("current_user_emailid",claims.get("user_email"));
            request.setAttribute("current_user_role",claims.get("user_role"));
            filterChain.doFilter(request,response);
       }
    }
}
