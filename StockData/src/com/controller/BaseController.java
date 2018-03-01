package com.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;



public class BaseController {

	protected static Log log = LogFactory.getLog(BaseController.class);
	
	
	public String addJson(String code,String msg,String json){
		return "{'code':'"+code+"','msg':'"+msg+"','json':"+json+"}";
	}
	
	public String addJson(String code,String msg,String json,String page){
		return "{'code':'"+code+"','msg':'"+msg+"','json':"+json+",'page':"+page+"}";
	}
	
	public String add2Json(String code,String msg,String json,String json2){
		return "{'code':'"+code+"','msg':'"+msg+"','json':"+json+",'json2':"+json2+"}";
	}
	
	public String add2Json(String code,String msg,Object json,Object json2){
		return "{'code':'"+code+"','msg':'"+msg+"','json':"+json+",'json2':"+json2+"}";
	}
	
}