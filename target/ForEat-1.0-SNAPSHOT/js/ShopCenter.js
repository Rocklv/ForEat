//页面加载完成之后加载个人信息数据
$(document).ready(function () {

    if (shopPhone == "null"){
        alert("登陆失败请重新登陆！");
        location.href="ShopLogin.jsp";
    }
    var clientJson = new Object();
    clientJson.shopPhone = shopPhone;
    $.ajax({
        type:"post",
        url:baseUrl+"index.jsp?control=Shop&method=shopCenter",
        dataType:'json',
        data:{
            'clientJson': JSON.stringify(clientJson)
        },
        success: function (serverJson) {
            var data = document.getElementsByClassName('data');
            data[0].innerHTML = serverJson.shopName;
            data[1].innerHTML = serverJson.shopPhone;

            //根据数据库中state字段的值判断商家的状态
            if(serverJson.shopState == "1"){
                data[2].innerHTML = "在线";
            }
            var textarea = document.getElementById('textarea');
            textarea.innerHTML = serverJson.shopDetail;
        },
        error: function () {
            alert("登陆失败请重新登陆！");
            location.href="ShopLogin.jsp";
        }
    });
});