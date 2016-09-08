$(document).ready(function () {
    $.ajax({
        type: 'post',
        url: baseUrl+'index.jsp?control=Food&method=foodList',
        dataType: 'json',
        data:{
            'clientJson': JSON.stringify(clientJson)
        },
        success: function (sJson) {

            var data = sJson.serverJson;
            document.getElementById("shopName").innerHTML= data[0].shopName;

            for (var i=0; i< data.length; i++){
                var list = "<div class='list'>"+
                            "<img src='img/food.jpg' alt='商家logo' />"+
                                "<div class='list-wrap'>"+
                                    "<div class='list-part' onclick='foodDetail(\""+data[i].foodId+"\")'>"+
                                        "<p class='name'>"+data[i].foodName+"</p>"+
                                        "<p class='time'>"+data[i].foodPrice+"</p>"+
                                    "</div>"+
                                    "<div class='list-part'>"+
                                        "<p class='orderId'>"+data[i].foodDetail+"</p>"+
                                        "<button onclick='orderAdd(\""+data[i].foodId+"\",\""+data[i].shopId+"\",\""+userPhone+"\")' class='btn-confirm'>确认下单</button>"+
                                    "</div>"+
                                "</div>"+
                            "</div>";
                $("#list").append(list);
            }
        }
    });
});

function foodDetail(foodId) {
    setTimeout(location.href="UserFoodDetail.jsp?foodId="+foodId,1000);
}

function orderAdd(food_id,shop_id,user_id) {
    var clientJson = new Object();
    clientJson.foodId = food_id;
    clientJson.shopId = shop_id;
    clientJson.userId = user_id;

    $.ajax({
        type: 'post',
        url: baseUrl + 'index.jsp?control=Orders&method=orderAdd',
        dataType: 'json',
        data: {
            'clientJson': JSON.stringify(clientJson)
        },
        success: function (sJson) {
            alert(sJson.message);
        }
    });
}