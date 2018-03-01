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
     * ��������ӳ�䵽��Ҫ���ص�·�� 
 
    private String mappingURL; 
 
    public void setMappingURL(String mappingURL) { 
               this.mappingURL = mappingURL; 
    } 
  */  
    /** 
     * ��ҵ��������������֮ǰ������ 
     * �������false 
     *     �ӵ�ǰ������������ִ��������������afterCompletion(),���˳��������� 
     * �������true 
     *    ִ����һ��������,ֱ�����е���������ִ����� 
     *    ��ִ�б����ص�Controller 
     *    Ȼ�������������, 
     *    �����һ������������ִ�����е�postHandle() 
     *    �����ٴ����һ������������ִ�����е�afterCompletion()
     *    
     * ����˵��
     * 1.��˳��ִ�и��������е�preHandle������������ִ�и��������е�postHandle�������������ִ���������е�afterCompletion������
     * 2.�����������κ�һ��postHandle����false����������ִ�е�postHandleҲ��ִ�С�
     */  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
        /*if ("GET".equalsIgnoreCase(request.getMethod())) {  
            RequestUtil.saveRequest();  
        }  */
        log.info("==============ִ��˳��: 1��preHandle================");  
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());
        
        log.info("requestUri:"+requestUri);  
        log.info("contextPath:"+contextPath);  
        log.info("url:"+url);
        Object o = request.getSession().getAttribute("backUser");
        if(url.indexOf(".action")>0){
            //�����action����У�����ajax��ȡ����ʱ����
            return true;
        }else{
        	//�������html��jsp����У���ҳ����������js��css���ļ���
        	if(url.indexOf(".html")>0||url.indexOf(".jsp")>0){
	            if(o != null){
	            	log.info("�ѵ�¼��"+o); 
	            	//request.setAttribute("sessionBackUser", o.toString());
	                //response.sendRedirect("/diamond/backstage/head/nav.html?name="+o.toString());
	                return true;
	            }else{
	            	response.sendRedirect(request.getContextPath()+"/backlogin.html");
	            	log.info("δ��¼�������¼����");
	            	return false;
	            }
        	}
        	return true;
        }
    	
    }  

    /** 
     * ��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ��� 
     * ����modelAndView�м������ݣ����統ǰʱ�� 
     */  
    @Override  
    public void postHandle(HttpServletRequest request,  
                           HttpServletResponse response, Object handler,  
                           ModelAndView modelAndView) throws Exception {  
        log.info("==============ִ��˳��: 2��postHandle================");  
        /*int count = SessionListener.getCount();
        log.info("��ǰ��������="+count+",��ǰ��¼����="+SessionListener.getUserCount());
        if(modelAndView != null){  //���뵱ǰʱ��  
            modelAndView.addObject("var", "����postHandle");  
        }*/
    }  
  
    /** 
     * ��DispatcherServlet��ȫ����������󱻵���,������������Դ�� 
     * 
     * �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion() 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
                                HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
        //log.debug("==============ִ��˳��: 3��afterCompletion================");  
    }  
  
}
