<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String phone = request.getParameter("phone");

	//判断phone参数是否为空
	if (phone != null && !"".equals(phone)) {
		//将用户的手机号放入session供其它页面获取
		session.setAttribute("shopPhone", phone);
	}else {
		//直接从session中获取用户手机号
		phone = (String) session.getAttribute("shopPhone");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base id="basePath" href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>商店管理中心</title>
	<link rel="stylesheet" href="css/ShopDefault.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/Center.css">
</head>
<body class="container-fluid">
	<div id="nav">
		<a href="ShopFoodList.jsp"><i class="icon-food"></i></a>
		<a href="ShopOrderList.jsp"><i class="icon-list-alt"></i></a>
		<a href="ShopCenter.jsp"><i class="icon-home selected"></i></a>
	</div>
	<div id="Content">
		<img id="centerlogo" src="img/Starbucks.jpg" alt="">
		<div id="center-wrap">
			<div class="information">
				<p class="itom">店名：</p>
				<p class="data">Starbucks</p>
			</div>
			<div class="information">
				<p class="itom">手机号：</p>
				<p class="data">13777841037</p>
			</div>
			<div class="information">
				<p class="itom">状态：</p>
				<p class="data">在线</p>
			</div>
			<div class="information">
				<p class="itom">店家详情：</p>
				<textarea id="textarea"></textarea>
			</div>
			<button id="foodAdd">添加餐品</button>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript">
		var shopPhone = <%=phone%>;

		var baseUrl = document.getElementById('basePath').href;

		//页面加载完成之后加载个人信息数据
		$(document).ready(function () {

			var clientJson = new Object();
			clientJson.shopPhone = shopPhone;
			$.ajax({
				type:"post",
				url:baseUrl+"index.jsp?control=Shop&method=ShopCenter",
				dataType:'json',
				data:{
					'clientJson': JSON.stringify(clientJson)
				},
				success: function (serverJson) {
					var data = document.getElementsByClassName('data');
					data[0].innerHTML = serverJson.shopName;
					data[1].innerHTML = serverJson.shopPhone;

					//根据数据库中state字段的值判断商家的状态
					if(serverJson.shopState == "1"){
						data[2].innerHTML = "在线";
					}
					var textarea = document.getElementById('textarea');
					textarea.innerHTML = serverJson.shopDetail;
				},
				error: function () {
					alert("登陆失败请重新登陆！");
					setTimeout(location.href="ShopLogin.jsp",2000);
				}
			});
		});
	</script>
</body>
</html>