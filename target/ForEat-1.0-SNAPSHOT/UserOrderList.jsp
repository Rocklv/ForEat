<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userPhone = (String) session.getAttribute("userPhone");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>用户订单列表</title>
	<link rel="stylesheet" href="css/UserDefault.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/UserForList.css">
</head>
<body class="container-fluid">
	<div id="nav">
        <a href="ShopList.jsp"><i class="icon-food"></i></a>
        <a href="UserOrderList.jsp"><i class="icon-list-alt selected"></i></a>
        <a href="UserCenter.jsp"><i class="icon-user"></i></a>
	</div>
	<div id="Content">
		<ul id="logo"></ul>
		<div id="list">
			<%--<div class="list">--%>
				<%--<img src="img/Starbucks.jpg" alt="商家logo" />--%>
				<%--<div class="list-wrap">--%>
					<%--<div class="list-part">--%>
						<%--<p class="name">FoodName Pizza</p>--%>
						<%--<p class="time">2016.7.1</p>--%>
					<%--</div>--%>
					<%--<div class="list-part">--%>
						<%--<p class="orderId">2016070113777841037</p>--%>
						<%--&lt;%&ndash;<button onclick="" class="btn-confirm">确认收货</button>&ndash;%&gt;--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>

		</div>
	</div>
    <script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript">
        var baseUrl = '<%=basePath%>';

        $(function () {
            var clientJson = new Object();
            clientJson.userPhone = '<%=userPhone%>';

            $.ajax({
                type: 'post',
                url: baseUrl+'index.jsp?control=Orders&method=orderList',
                dataType: 'json',
                data: {
                    clientJson: JSON.stringify(clientJson)
                },
                success: function (sJson) {
                    var data = sJson.serverJson;
                    for (var i=0; i<data.length; i++){
                        var list = "<div class='list'>"+
                                        "<img src='img/Starbucks.jpg' alt='商家logo' />"+
                                        "<div class='list-wrap'>"+
                                            "<div class='list-part' onclick='orderDetail(\""+data[i].orderId+"\")'>"+
                                                "<p class='name'>"+data[i].foodName+"</p>"+
                                                "<p class='time'>"+data[i].createTime+"</p>"+
                                            "</div>"+
                                            "<div class='list-part'>"+
                                                "<p class='orderId'>"+data[i].orderId+"</p>"+
                                                getOrderState(data[i])+
                                            "</div>"+
                                        "</div>"+
                                    "</div>";
                        $("#list").append(list);
                    }
                }
            });

        });

        //根据订单“状态”呈现不同的业务服务
        //0 表示用户已下单
        //1 表示商家已接单
        //2 表示用户已收获
        function getOrderState(data) {
            switch (data.state){
                case "0":
                    return "<button class='btn-info'>等待接单</button>"
                    break;
                case "1":
                    return "<button onclick='orderConfirm(\""+data.orderId+"\")' class='btn-confirm'>确认收货</button>"
                    break;
                case "2":
                    return "<button onclick='orderDelete(\""+data.orderId+"\")' class='btn-danger'>删除记录</button>"
                default:
                    break;
            }
        }

        //订单详情
        function orderDetail(orderId) {
            location.href="UserOrderDetail.jsp?orderId="+orderId;
        }

        function orderConfirm(orderId) {
            var clientJson = new Object();
            clientJson.orderId = orderId;
            $.ajax({
                type: 'post',
                url: baseUrl+'index.jsp?control=Orders&method=orderConfirm',
                dataType: 'json',
                data: {
                    clientJson:JSON.stringify(clientJson)
                },
                success: function (sJson) {
                    alert(sJson.message);
                    location.href="UserOrderList.jsp";
                }
            });
        }

        function orderDelete(orderId) {
            var clientJson = new Object();
            clientJson.orderId = orderId;
            $.ajax({
                type: 'post',
                url: baseUrl+'index.jsp?control=Orders&method=orderDelete',
                dataType: 'json',
                data:{
                    clientJson:JSON.stringify(clientJson)
                },
                success: function (sJson) {
                    alert(sJson.message);
                    location.href="UserOrderList.jsp";
                }
            });
        }
	</script>
</body>
</html>