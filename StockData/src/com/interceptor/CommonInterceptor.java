package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonInterceptor extends HandlerInterceptorAdapter{  
    
	ApplicationContext application;
	private final Log log = LogFactory.getLog(CommonInterceptor.class);  
    /* 
     * 利用正则映射到需要拦截的路径 
 
    private String mappingURL; 
 
    public void setMappingURL(String mappingURL) { 
               this.mappingURL = mappingURL; 
    } 
  */  
    /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     *    
     * 简单来说：
     * 1.正顺序执行各拦截器中的preHandle方法，再逆序执行各拦截器中的postHandle方法，最后逆序执行拦截器中的afterCompletion方法。
     * 2.各拦截器中任何一个postHandle返回false，则其他后执行的postHandle也不执行。
     */  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
        /*if ("GET".equalsIgnoreCase(request.getMethod())) {  
            RequestUtil.saveRequest();  
        }  */
        log.info("==============执行顺序: 1、preHandle================");  
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());
        
        log.info("requestUri:"+requestUri);  
        log.info("contextPath:"+contextPath);  
        log.info("url:"+url);
        Object o = request.getSession().getAttribute("backUser");
        if(url.indexOf(".action")>0){
            //如果是action则放行，避免ajax获取参数时跳出
            return true;
        }else{
        	//如果不是html与jsp则放行（令页面正常引入js，css等文件）
        	if(url.indexOf(".html")>0||url.indexOf(".jsp")>0){
	            if(o != null){
	            	log.info("已登录："+o); 
	            	//request.setAttribute("sessionBackUser", o.toString());
	                //response.sendRedirect("/diamond/backstage/head/nav.html?name="+o.toString());
	                return true;
	            }else{
	            	response.sendRedirect(request.getContextPath()+"/backlogin.html");
	            	log.info("未登录：进入登录界面");
	            	return false;
	            }
        	}
        	return true;
        }
    	
    }  

    /** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 
     * 可在modelAndView中加入数据，比如当前时间 
     */  
    @Override  
    public void postHandle(HttpServletRequest request,  
                           HttpServletResponse response, Object handler,  
                           ModelAndView modelAndView) throws Exception {  
        log.info("==============执行顺序: 2、postHandle================");  
        /*int count = SessionListener.getCount();
        log.info("当前在线人数="+count+",当前登录人数="+SessionListener.getUserCount());
        if(modelAndView != null){  //加入当前时间  
            modelAndView.addObject("var", "测试postHandle");  
        }*/
    }  
  
    /** 
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等 
     * 
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
                                HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
        //log.debug("==============执行顺序: 3、afterCompletion================");  
    }  
  
}
