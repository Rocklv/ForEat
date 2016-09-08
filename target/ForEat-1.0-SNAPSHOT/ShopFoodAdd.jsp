<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>餐品添加</title>
	<link rel="stylesheet" href="css/ShopDefault.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/Center.css">
	<link rel="stylesheet" type="text/css" href="css/ShopFoodAdd.css">
	<style type="text/css">
	</style>
</head>
<body class="container-fluid">
	<div id="nav">
		<a href="ShopFoodList.jsp"><i class="icon-food selected"></i></a>
		<a href="ShopOrderList.jsp"><i class="icon-list-alt"></i></a>
		<a href="ShopCenter.jsp"><i class="icon-home"></i></a>
	</div>
	<div id="Content">
		<img id="centerlogo" src="img/Starbucks.jpg" alt="">
		<div id="center-wrap">
			<div class="information">
				<p class="key">餐品名称 :</p>
				<input type="text" class="foodData" />
			</div>
			<div class="information">
				<p class="key">餐品价格 :</p>
				<input type="text" class="foodData" />
			</div>
			<div class="information">
				<p class="key">餐品图片 :</p>
				<input type="file" class="foodData" />
			</div>
			<div class="information">
				<p class="key">餐品详情 :</p>
				<textarea></textarea>
			</div>
			<button id="foodAdd">确定添加</button>
		</div>
	</div>
	
	<script type="text/javascript">
	</script>
</body>
</html>