<%@page language="java" import="core.MyReflect" pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//利用请求中的 参数 获取 控制器类 及对应方法的名
	String controller = request.getParameter("control");
	String method = request.getParameter("method");
	
	//获取ajax请求中的json数据
	String jsonStr = request.getParameter("clientJson");
	JSONObject json = JSONObject.fromObject(jsonStr);

    //判断控制器类和对应方法 传入前端的入参和response对象
	if (controller != null && method != null) {
		MyReflect.ReflectControl(controller, method, json, response);
	}


%>