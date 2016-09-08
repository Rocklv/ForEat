//页面加载完成之后加载个人信息数据
$(document).ready(function () {

    var clientJson = new Object();
    clientJson.userPhone = userPhone;
    $.ajax({
        type:"post",
        url:baseUrl+"index.jsp?control=User&method=userCenter",
        dataType:'json',
        data:{
            'clientJson': JSON.stringify(clientJson)
        },
        success: function (serverJson) {
            var data = document.getElementsByClassName('data');
            data[0].innerHTML = serverJson.userName;
            data[1].innerHTML = serverJson.userPhone;
            data[2].innerHTML = serverJson.userAddress;
        },
        error: function () {
            alert("登陆失败请重新登陆！");
            setTimeout(location.href="UserLogin.jsp",2000);
        }
    });
});