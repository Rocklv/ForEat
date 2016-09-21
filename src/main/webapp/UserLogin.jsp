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
	<title>用户登陆</title>
	<link rel="stylesheet" href="css/UserDefault.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/UserLogin.css">
</head>
<body class="container-fluid">
	<div id="Mainbody">
		<ul id="logo"></ul>
		<div>
			<i class="icon-user"></i>
			<span>手机号</span><br>
			<input id="phone"  type="text" />
		</div>
		<div>
			<i class="icon-lock"></i>
			<span>密码</span><br>
			<input id="password"  type="password" />
		</div>
		<button id="submit" type="button" class="btn btn-success">登陆</button>
		<a id="Enter" href="ShopLogin.jsp">商家入口</a>
		<a id="Register" href="UserRegister.jsp">注册用户</a>
	</div>
</body>
<script type="text/javascript"  src="js/jquery-3.0.0.min.js"></script>
<script type="text/javascript">

	var baseUrl = document.getElementById('basePath').href;

	document.getElementById("submit").onclick = function () {
		var clientJson = new Object();
		clientJson.phone = document.getElementById("phone").value;
		clientJson.password = document.getElementById("password").value;

		$.ajax({
			type:"post",
			url:baseUrl+"index.jsp?control=User&method=userLogin",
			dataType:'json',
			data:{
				'clientJson':JSON.stringify(clientJson)
			},
			success: function (sJson) {
                if (sJson.resultCode != 0){
                    //登陆失败则重新访问登陆界面
                    alert(sJson.resultMessage);
                    location.href="UserLogin.jsp";
                }else {
                    //登陆成功则跳转至个人信息界面
                    location.href="UserCenter.jsp?phone="+clientJson.phone;
                }
			}
		});
	}
</script>
</html>