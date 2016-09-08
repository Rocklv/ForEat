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