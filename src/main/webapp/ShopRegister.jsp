<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base id="basePath" href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>商家注册</title>
	<link rel="stylesheet" href="css/ShopDefault.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/ShopRegister.css">
</head>
<body class="container-fluid">
	<div id="Mainbody">
		<ul id="logo"></ul>
		<div>
			<i class="icon-phone"></i>
			<span>手机号</span><br>
			<input id="shopPhone" type="text" />
		</div>
		<div>
			<i class="icon-user"></i>
			<span>店名</span><br>
			<input id="shopName" type="text" />
		</div>
		<div>
			<i class="icon-lock"></i>
			<span>密码</span><br>
			<input type="password" />
		</div>
		<div>
			<i class="icon-lock"></i>
			<span>密码确认</span><br>
			<input id="shopPassword" type="password" />
		</div>
		<div>
			<button id="btnSubmit" class="btn btn-success">注册</button>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript">

		var baseUrl = document.getElementById('basePath').href;
		var input = document.getElementsByTagName('input');
		for (var i = 0; i < input.length; i++) {
			input[i].onfocus = function(){
				this.style.borderBottom = "2px solid #fff";
			}
			input[i].onblur = function(){
				this.style.borderBottom = "2px solid #666";
				if (!this.value) {
					this.style.borderBottom="2px solid red";
				}
			}
		}
		var submit = document.getElementById("btnSubmit");
		var span = document.getElementsByTagName('span');
		submit.onclick = function() {
			var count = 0;	//记录非法操作的次数，以便判断请求是否发送
			for (var j = 0; j < input.length; j++) {
				if (!input[j].value) {
					alert(span[j].innerHTML+" 未输入");
					count++;
				}
			}
			if (input[2].value != input[3].value){
				alert("两次输入密码不一样，请重新输入");
				count++;
			}
			//count值为0时，发送注册请求
			if (count == 0) {
				AjaxPost();
			}
		}
		function AjaxPost() {

			var clientJson = new Object();
			clientJson.shopPhone = document.getElementById('shopPhone').value;
			clientJson.shopName = document.getElementById('shopName').value;
			clientJson.shopPassword = document.getElementById('shopPassword').value;

			alert(clientJson.shopPhone+" "+clientJson.shopName+" "+clientJson.shopPassword);

			$.ajax({
				type: "post",
				url: baseUrl+"index.jsp?control=Shop&method=shopRegister",
				dataType: 'json',
				data:{
					'clientJson': JSON.stringify(clientJson)
				},
				success: function (serverJson) {
					if(serverJson.resultCode = 1){
						//登陆成功则跳转至用户登录界面
						setTimeout(location.href="ShopLogin.jsp",2000);
					}else {
						alert(serverJson.resultMessage);
						setTimeout(location.href="ShopLogin.jsp",2000);
					}
				},
				error: function () {
					alert("fail");
				}
			});
		}
	</script>
</body>
</html>