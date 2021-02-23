package com.tms.gateway.security;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tms.gateway.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


public class AddUserIdToHeader extends ZuulFilter {

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        String token = jwtTokenUtils.resolveToken(request);

        if (uri.equals("/tests-service/features/scenarios") || uri.equals("/users-service/api/me")) {
            ctx.addZuulRequestHeader("user-id", jwtTokenUtils.getUserDetails(token).getUsername());
        }
        return null;
    }

}
