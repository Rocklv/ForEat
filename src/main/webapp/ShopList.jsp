<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String phone = (String) session.getAttribute("userPhone");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base id="basePath" href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>商店列表</title>
	<link rel="stylesheet" href="css/UserDefault.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/UserForList.css">
</head>
<body class="container-fluid">
	<div id="nav">
		<a href="ShopList.jsp"><i class="icon-food selected"></i></a>
		<a href="UserOrderList.jsp"><i class="icon-list-alt"></i></a>
		<a href="UserCenter.jsp"><i class="icon-user"></i></a>
	</div>
	<div id="Content">
		<ul id="logo"></ul>
		<div id="list">
			<%--<div class="list">--%>
				<%--<img src="img/Starbucks.jpg" alt="商家logo" />--%>
				<%--<div class="list-wrap">--%>
					<%--<div class="list-part">--%>
						<%--<p class="name">shopName 星巴克</p>--%>
					<%--</div>--%>
					<%--<div class="list-part">--%>
						<%--<p class="detail">商家电话：shopPhone</p>--%>
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
			clientJson.userPhone = <%=phone%>;

			$.ajax({
				type: 'post',
				url: baseUrl+'index.jsp?control=Shop&method=shopList',
				dataType: 'json',
				data:{
					'clientJson': JSON.stringify(clientJson)
				},
				success: function (sJson) {
					//获取json对象数组
					var data = sJson.serverJson;

					for (var i=0; i< data.length; i++){
						var list = "<div id='list_"+i+"' class='list' onclick='UserFoodList(\""+data[i].shopPhone+"\")'>"+
										"<img src='img/Starbucks.jpg' alt='商家logo' />"+
										"<div class='list-wrap'>"+
											"<div class='list-part'>"+
												"<p class='name'>"+data[i].shopName+"</p>"+
											"</div>"+
											"<div class='list-part'>"+
												"<p class='detail'>商家电话："+data[i].shopPhone+"</p>"+
											"</div>"+
										"</div>"+
									"</div>";
						$("#list").append(list);
					}
				}
			});
		});

		function UserFoodList(shopPhone) {
			setTimeout(location.href="UserFoodList.jsp?id="+shopPhone,1000);
		}
	</script>
</body>
</html>