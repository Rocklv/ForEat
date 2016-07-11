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
	<title>商店订单列表</title>
	<link rel="stylesheet" href="css/ShopDefault.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/ShopForList.css">
</head>
<body class="container-fluid">
	<div id="nav">
		<a href=""><i class="icon-food"></i></a>
		<a href=""><i class="icon-list-alt selected"></i></a>
		<a href=""><i class="icon-user"></i></a>
	</div>
	<div id="Content">
		<ul id="logo"></ul>
		<div id="list">
			<div class="list">
				<img src="img/Starbucks.jpg" alt="商家logo" />
				<div class="list-wrap">
					<div class="list-part">
						<p class="name">foodName</p>
						<p class="time">creatTime</p>
					</div>
					<div class="list-part">
						<p class="orderId">orderId</p>
						<button onclick="" class="btn-confirm">接单</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	</script>
</body>
</html>