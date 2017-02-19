<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String shopPhone = (String) session.getAttribute("shopPhone");
    String imgLogo="img/food.jpg";
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
				<input id="foodName" name="foodName" type="text" class="foodData" />
			</div>
			<div class="information">
				<p class="key">餐品价格 :</p>
				<input id="foodPrice" name="foodPrice" type="text" class="foodData" />
			</div>
            <form id="foodLogoUpload" action="foodLogoUpload" method="post" enctype="multipart/form-data">
                <div class="information">
                    <p class="key">餐品图片 :</p>
                    <input id="submit" type="submit" value="上传" />
                    <input id="foodLogo" name="foodLogo" type="file" class="foodData" />
                    <img id="imgLogo" src="<%=imgLogo%>" alt="foodLogo" />
                </div>
            </form>
			<div class="information">
				<p class="key">餐品详情 :</p>
				<textarea id="foodDetail" name="foodDetail"></textarea>
			</div>

			<button id="foodAdd">确定添加</button>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript">
        var baseUrl = '<%=basePath%>';
        var shopPhone = '<%=shopPhone%>';
	</script>
    <script type="text/javascript" src="js/ShopFoodAdd.js"></script>
</body>
</html>