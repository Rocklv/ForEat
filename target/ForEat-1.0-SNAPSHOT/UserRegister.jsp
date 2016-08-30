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
	<title>用户注册</title>
	<link rel="stylesheet" href="css/UserDefault.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/UserRegister.css">
</head>
<body class="container-fluid">
	<div id="Mainbody">
		<ul id="logo"></ul>
		<div>
			<i class="icon-phone"></i>
			<span>手机号</span><br>
			<input id="userPhone" type="text" />
		</div>
		<div>
			<i class="icon-user"></i>
			<span>用户名</span><br>
			<input id="userName" type="text" />
		</div>
		<div>
			<i class="icon-home"></i>
			<span>送餐地址</span><br>
			<input id="userAddress" type="text" />
		</div>
		<div>
			<i class="icon-lock"></i>
			<span>密码</span><br>
			<input type="password" />
		</div>
		<div>
			<i class="icon-lock"></i>
			<span>密码确认</span><br>
			<input id="userPassword" type="password" />
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
		var count = 0;	//记录非法操作的次数，以便判断请求是否发送
		submit.onclick = function() {

			for (var j = 0; j < input.length; j++) {
				if (!input[j].value) {
					alert(span[j].innerHTML+" 未输入");
					count++;
				}
			}
			if (input[3].value != input[4].value){
				alert("两次输入密码不一样，请重新输入");
				count++;
			}
			//count值为0时，发送注册请求
			if (count == 0){
				AjaxPost();
			}
		}

		function AjaxPost() {

			var clientJson = new Object();
			clientJson.userPhone = document.getElementById('userPhone').value;
			clientJson.userName = document.getElementById('userName').value;
			clientJson.userAddress = document.getElementById('userAddress').value;
			clientJson.userPassword = document.getElementById('userPassword').value;

//			alert(clientJson.userPhone+" "+clientJson.userName+" "+clientJson.userAddress+" "+clientJson.userPassword);

			$.ajax({
				type: "post",
				url: baseUrl+"index.jsp?control=User&method=userRegister",
				dataType: 'json',
				data:{
					'clientJson': JSON.stringify(clientJson)
				},
				success: function (serverJson) {
					if(serverJson.resultCode = 1){
						//登陆成功则跳转至用户登录界面
						setTimeout(location.href="UserLogin.jsp",2000);
					}else {
						alert(serverJson.resultMessage);
						setTimeout(location.href="UserLogin.jsp",2000);
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