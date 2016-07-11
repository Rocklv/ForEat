<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>餐品详情</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/Detail.css">
</head>
<body class="container-fluid">
	<div id="nav">
		<a href=""><i class="icon-food selected"></i></a>
		<a href=""><i class="icon-list-alt"></i></a>
		<a href=""><i class="icon-user"></i></a>
	</div>
	<div id="Content">
		<img id="centerlogo" src="img/food.jpg" alt="">
		<div id="center-wrap">
			<div class="information">
				<p class="itom">餐品名称：</p>
				<p class="data">foodName</p>
			</div>
			<div class="information">
				<p class="itom">商家名称：</p>
				<p class="data">shopName</p>
			</div>
			<div class="information">
				<p class="itom">商家电话：</p>
				<p class="data">shopPhone</p>
			</div>
			<div class="information">
				<p class="itom">餐品详情：</p>
				<textarea class="detail">foodDetail</textarea>
			</div>
		</div>
		
	</div>
	
	<script type="text/javascript">
	</script>
</body>
</html>