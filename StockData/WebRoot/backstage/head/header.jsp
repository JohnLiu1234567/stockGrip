<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="/StockData/backstage/js/jquery-1.7.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	function getDateWeek() {
		todayDate = new Date();
		date = todayDate.getDate();
		month = todayDate.getMonth() + 1;
		year = todayDate.getYear();
		var dateweek = "今天是:";
		if (navigator.appName == "Netscape") {
			dateweek = dateweek + (1900 + year) + "年" + month + "月" + date
					+ "日 ";
		}
		if (navigator.appVersion.indexOf("MSIE") != -1) {
			dateweek = dateweek + year + "年" + month + "月" + date + "日 ";
		}
		switch (todayDate.getDay()) {
		case 0:
			dateweek += "星期日";
			break;
		case 1:
			dateweek += "星期一";
			break;
		case 2:
			dateweek += "星期二";
			break;
		case 3:
			dateweek += "星期三";
			break;
		case 4:
			dateweek += "星期四";
			break;
		case 5:
			dateweek += "星期五";
			break;
		case 6:
			dateweek += "星期六";
			break;
		}
		return dateweek;
	}
	
	//退出
	function exit(){
		$.ajax({
			type:"post",
			url:"/diamond/backuser/logout.action",
			data:{},
			datatype:"text",
			success:function(data){
				data = eval("("+data+")");
				parent.location = "/diamond/backlogin.html";
			}
		});
	}
	
	function getSessionId(){
   var c_name = 'JSESSIONID';
   if(document.cookie.length>0){
      c_start=document.cookie.indexOf(c_name + "=")
      if(c_start!=-1){ 
        c_start=c_start + c_name.length+1 
        c_end=document.cookie.indexOf(";",c_start)
        if(c_end==-1) c_end=document.cookie.length
        return unescape(document.cookie.substring(c_start,c_end));
      }
   }
  }
	
	$(document).ready(function(){
		
		//var name = getSessionId();
		name = ${sessionScope.backuser};
		
		$("#name").text(name);
		$("#timetable").html(getDateWeek());
		
	});
	
	</script>
  </head>
  
  <body>
    <div class="htop">
		<div class="head">
			<div class="z">
				<span id="timetable"></span>，欢迎您 :<span id="name"></span>
			</div>
			<div class="y" style="float: right; margin-top: 10px;">
				<span style="line-height: 0px;top:0px; float: left; margin: 0px 30px; cursor: pointer;"
					onclick="exit();">退出账号</span>
			</div>
			<div style="clear: both"></div>
		</div>
	</div>
  </body>
</html>
