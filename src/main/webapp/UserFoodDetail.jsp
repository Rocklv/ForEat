<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//获取食物的id
	String foodId = request.getParameter("foodId");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base id="basePath" href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>餐品详情</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/Detail.css">
</head>
<body class="container-fluid">
	<div id="nav">
		<a href="ShopList.jsp"><i class="icon-food selected"></i></a>
		<a href="UserOrderList.jsp"><i class="icon-list-alt"></i></a>
		<a href="UserCenter.jsp"><i class="icon-user"></i></a>
	</div>
	<div id="Content">
		<img id="centerlogo" src="img/food.jpg" alt="">
		<div id="center-wrap">
			<div class="information">
				<p class="itom">餐品名称：</p>
				<p id="foodName" class="data"></p>
			</div>
			<div class="information">
				<p class="itom">商家名称：</p>
				<p id="shopName" class="data"></p>
			</div>
			<div class="information">
				<p class="itom">商家电话：</p>
				<p id="shopPhone" class="data"></p>
			</div>
			<div class="information">
				<p class="itom">餐品详情：</p>
				<textarea id="foodDetail" class="detail"></textarea>
			</div>
		</div>
		
	</div>
	<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript">
		$(function () {
			var baseUrl = "<%=basePath%>";
			var clientJson = new Object();
			clientJson.foodId = "<%=foodId%>";

			$.ajax({
				type: 'post',
				url: baseUrl +'index.jsp?control=Food&method=foodDetail',
				dataType: 'json',
				data: {
					'clientJson': JSON.stringify(clientJson)
				},
				success: function (sJson) {
					var data = sJson.serverJson;
                    $("#foodName").html(data.foodName);
                    $("#shopName").html(data.shopName);
                    $("#shopPhone").html(data.shopId);
                    $("#foodDetail").html(data.foodDetail);
				}
			});
		});
	</script>
</body>
</html>