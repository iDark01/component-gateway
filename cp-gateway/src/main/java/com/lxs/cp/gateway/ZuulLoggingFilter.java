package com.lxs.cp.gateway;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.client.IResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ZuulLoggingFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		RequestContext ctx = RequestContext.getCurrentContext();
		logger.info("Gateway routing :: {} -> {}", request.getRequestURI(), ((IResponse) ctx.get("ribbonResponse")).getRequestedURI());
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
}
