package com.heliang.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


@Component
public class MyFilter extends ZuulFilter {

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
 
        System.out.println(String.format("%s AccessUserNameFilter request to %s", request.getMethod(), request.getRequestURL().toString()));
 
        String username = request.getParameter("name");// 获取请求的参数
        if(null != username && username.equals("heliang")) {// 如果请求的参数不为空，且值为chhliu时，则通过
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);//设值，让下一个Filter看到上一个Filter的状态
            return null;
        }else{
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }
	}

	/**
	 * 返回一个boolean类型来判断该过滤器是否要执行，
	 * 所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效
	 */
	@Override
	public boolean shouldFilter() {
		return true;// 是否执行该过滤器，此处为true，说明需要过滤
	}

	/**
	 * 通过int值来定义过滤器的执行顺序
	 */
	@Override
	public int filterOrder() {
		return 0; //优先级为0，数字越大，优先级越低
	}

	
	/**
	 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
	 *	pre：可以在请求被路由之前调用
	 *	route：在路由请求时候被调用
	 *	post：在route和error过滤器之后被调用
	 *	error：处理请求时发生错误时被调用
	 */
	@Override
	public String filterType() {
		return "pre";//前置过滤器
	}
}
