<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String shopPhone = (String) session.getAttribute("shopPhone");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>用户餐品列表</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/ShopForList.css">
	<link rel="stylesheet" href="css/ShopFoodList.css">
</head>
<body class="container-fluid">
	<div id="nav">
		<a href="ShopFoodList.jsp"><i class="icon-food selected"></i></a>
		<a href="ShopOrderList.jsp"><i class="icon-list-alt"></i></a>
		<a href="ShopCenter.jsp"><i class="icon-home"></i></a>
	</div>
	<div id="Content">
		<div id="head">
			<img id="shopLogo" src="img/Starbucks.jpg" alt="">
			<p id="shopName"> 星巴克 </p>
		</div>
		<div id="list">
			<%--<div class="list">--%>
				<%--<img src="img/food.jpg" alt="商家logo" />--%>
				<%--<div class="list-wrap">--%>
					<%--<div class="list-part">--%>
						<%--<p class="name"> foodName Pizza</p>--%>
						<%--<p class="time"> price 27￥</p>--%>
					<%--</div>--%>
					<%--<div class="list-part">--%>
						<%--<p class="orderId"> foodDetail 鸡肉+海鲜+培根</p>--%>
						<%--<button onclick="" class="btn btn-danger">删除</button>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
		</div>
    </div>
    <script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
    <script type="text/javascript">
        var baseUrl = '<%=basePath%>';
        var clientJson = new Object();
        clientJson.shopPhone = '<%=shopPhone%>';
	</script>
    <script type="text/javascript" src="js/ShopFoodList.js"></script>
</body>
</html>