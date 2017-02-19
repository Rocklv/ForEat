//根路径
var baseUrl = document.getElementById('basePath').href;
//获取输入元素
var input = document.getElementsByTagName('input');
//空输入提示（下线变红）
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

//获取提交按钮元素
var submit = document.getElementById("btnSubmit");
//获取输入框标题元素
var span = document.getElementsByTagName('span');
//记录非法操作的次数，以便判断请求是否发送
var count = 0;
//为提交按钮添加单击事件监听
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

// 请求内容
function AjaxPost() {

    var clientJson = new Object();
    clientJson.userPhone = document.getElementById('userPhone').value;
    clientJson.userName = document.getElementById('userName').value;
    clientJson.userAddress = document.getElementById('userAddress').value;
    clientJson.userPassword = document.getElementById('userPassword').value;

    $.ajax({
        type: "post",
        url: baseUrl+"index.jsp?control=User&method=userRegister",
        dataType: 'json',
        data:{
            'clientJson':JSON.stringify(clientJson)
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