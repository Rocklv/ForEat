<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//	获取商家手机号来查询商家的餐品列表
	String shopPhone = request.getParameter("id");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base id="basePath" href="<%=basePath%>">
	<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>用户餐品列表</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/UserForList.css">
	<link rel="stylesheet" href="css/UserFoodList.css">
</head>
<body class="container-fluid">
	<div id="nav">
		<a href=""><i class="icon-food selected"></i></a>
		<a href=""><i class="icon-list-alt"></i></a>
		<a href=""><i class="icon-user"></i></a>
	</div>
	<div id="Content">
		<div id="head">
			<img src="img/Starbucks.jpg" alt="">
			<p id="shopName"></p>
		</div>
		<div id="list">
			<%--<div class="list">--%>
				<%--<img src="img/food.jpg" alt="商家logo" />--%>
				<%--<div class="list-wrap">--%>
					<%--<div class="list-part">--%>
						<%--<p class="name">foodNamePizza</p>--%>
						<%--<p class="time">price27￥</p>--%>
					<%--</div>--%>
					<%--<div class="list-part">--%>
						<%--<p class="orderId">foodDetail鸡肉+海鲜+培根</p>--%>
						<%--<button onclick="" class="btn-confirm">确认下单</button>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
			
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			var baseUrl = document.getElementById('basePath').href;
			var clientJson =new Object();
			clientJson.shopPhone = <%=shopPhone%>;
			$.ajax({
				type: 'post',
				url: baseUrl+'index.jsp?control=Food&method=UserFoodList',
				dataType: 'json',
				data:{
					'clientJson': JSON.stringify(clientJson)
				},
				success: function (sJson) {

					var data = sJson.serverJson;
					document.getElementById("shopName").innerHTML= data[0].shopName;
					for (var i=0; i< data.length; i++){
						var list = "<div class='list'>"+
										"<img src='img/food.jpg' alt='商家logo' />"+
											"<div class='list-wrap'>"+
												"<div class='list-part'>"+
													"<p class='name'>"+data[i].foodName+"</p>"+
													"<p class='time'>"+data[i].foodPrice+"</p>"+
												"</div>"+
												"<div class='list-part'>"+
													"<p class='orderId'>"+data[i].foodDetail+"</p>"+
													"<button onclick='OrderAdd("+data[i].foodId+")' class='btn-confirm'>确认下单</button>"+
												"</div>"+
											"</div>"+
										"</div>";
						$("#list").append(list);
					}
				}
			});
		});

		function OrderAdd(foodId) {

		}
	</script>
</body>
</html>