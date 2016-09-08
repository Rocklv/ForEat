<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base id="basePath" href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>用户注册</title>
	<link rel="stylesheet" href="css/UserDefault.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/UserRegister.css">
</head>
<body class="container-fluid">
	<div id="Mainbody">
		<ul id="logo"></ul>
		<div>
			<i class="icon-phone"></i>
			<span>手机号</span><br>
			<input id="userPhone" type="text" />
		</div>
		<div>
			<i class="icon-user"></i>
			<span>用户名</span><br>
			<input id="userName" type="text" />
		</div>
		<div>
			<i class="icon-home"></i>
			<span>送餐地址</span><br>
			<input id="userAddress" type="text" />
		</div>
		<div>
			<i class="icon-lock"></i>
			<span>密码</span><br>
			<input type="password" />
		</div>
		<div>
			<i class="icon-lock"></i>
			<span>密码确认</span><br>
			<input id="userPassword" type="password" />
		</div>
		<div>
			<button id="btnSubmit" class="btn btn-success">注册</button>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="js/UserRegister.js"></script>
</body>
</html>