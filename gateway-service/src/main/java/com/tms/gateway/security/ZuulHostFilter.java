package com.tms.gateway.security;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZuulHostFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(ZuulHostFilter.class);

    public static final String HEADER_HOST = "Host";
    public static final String HEADER_X_FORWARDED_HOST = "X-Forwarded-Host";

    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getHeaders(HEADER_X_FORWARDED_HOST).hasMoreElements();
    }

    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String host = ctx.getRequest().getHeaders(HEADER_X_FORWARDED_HOST).nextElement();
        ctx.getZuulRequestHeaders().put(HEADER_HOST, host);

        logger.info("Request from host: " + host);
        return null;
    }
}
