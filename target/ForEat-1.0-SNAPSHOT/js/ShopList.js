$(document).ready(function () {
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
    location.href="UserFoodList.jsp?id="+shopPhone;
}