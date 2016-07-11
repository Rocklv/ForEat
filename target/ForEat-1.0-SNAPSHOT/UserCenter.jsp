<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String phone = request.getParameter("phone");

	//判断phone参数是否为空
	if (phone != null && !"".equals(phone)) {
		//将用户的手机号放入session供其它页面获取
		session.setAttribute("userPhone", phone);
	}else {
		//直接从session中获取用户手机号
		phone = (String) session.getAttribute("userPhone");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base id="basePath" href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>用户个人中心</title>
	<link rel="stylesheet" href="css/UserDefault.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/Center.css">
</head>
<body class="container-fluid">
	<div id="nav">
		<a href="ShopList.jsp"><i class="icon-food"></i></a>
		<a href="UserOrderList.jsp"><i class="icon-list-alt"></i></a>
		<a href="UserCenter.jsp"><i class="icon-user selected"></i></a>
	</div>
	<div id="Content">
		<img id="centerlogo" src="img/userlogo.gif">
		<div id="center-wrap">
			<div class="information">
				<p class="itom">昵称：</p>
				<p class="data">吃货</p>
			</div>
			<div class="information">
				<p class="itom">手机号：</p>
				<p class="data">13777841037</p>
			</div>
			<div class="information">
				<p class="itom">默认地址：</p>
				<p class="data">浙江省江干区白羊街道浙江传媒学院</p>
			</div>
		</div>
	</div>
	<script type="text/javascript"  src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript">

		var userPhone = <%=phone%>;

		var baseUrl = document.getElementById('basePath').href;

		//页面加载完成之后加载个人信息数据
		$(document).ready(function () {

			var clientJson = new Object();
			clientJson.userPhone = userPhone;
			$.ajax({
				type:"post",
				url:baseUrl+"index.jsp?control=User&method=UserCenter",
				dataType:'json',
				data:{
					'clientJson': JSON.stringify(clientJson)
				},
				success: function (serverJson) {
					var data = document.getElementsByClassName('data');
					data[0].innerHTML = serverJson.userName;
					data[1].innerHTML = serverJson.userPhone;
					data[2].innerHTML = serverJson.userAddress;
				},
				error: function () {
					alert("登陆失败请重新登陆！");
					setTimeout(location.href="UserLogin.jsp",2000);
				}
			});
		});
	</script>
</body>
</html>