<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String orderId = request.getParameter("orderId");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>用户订单详情</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/Detail.css">
</head>
<body class="container-fluid">
	<div id="nav">
		<a href="ShopList.jsp"><i class="icon-food"></i></a>
		<a href="UserOrderList.jsp"><i class="icon-list-alt selected"></i></a>
		<a href="UserCenter.jsp"><i class="icon-user"></i></a>
	</div>
	<div id="Content">
		<img id="food-logo" alt="商品logo">
		<div id="center-wrap">
			<div class="information">
				<p class="itom">订单状态：</p>
				<p id="orderState" class="data">orderState</p>
			</div>
			<div class="information">
				<p class="itom">订单号码：</p>
				<p id="orderId" class="data">orderId</p>
			</div>
			<div class="information">
				<p class="itom">创建时间：</p>
				<p id="createTime" class="data">createTime</p>
			</div>
			<div class="information">
				<p class="itom">餐品名称：</p>
				<p id="foodName" class="data">foodName</p>
			</div>
			<div class="information">
				<p class="itom">商家电话：</p>
				<p id="shopPhone" class="data">shopPhone</p>
			</div>
            <div class="information">
                <p class="itom">用户电话：</p>
                <p id="userPhone" class="data">userPhone</p>
            </div>
			<div class="information">
				<p class="itom">餐品详情：</p>
				<textarea id="foodDetail" class="detail">foodDetail</textarea>
			</div>
		</div>
		
	</div>
	<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript">
        var baseUrl = '<%=basePath%>';
        var clientJson = new Object();
        clientJson.orderId = '<%=orderId%>';
	</script>
    <script type="text/javascript" src="js/UserOrderDetail.js"></script>
</body>
</html>