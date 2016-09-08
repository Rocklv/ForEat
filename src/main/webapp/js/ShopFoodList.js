$(function () {
    // 请求商店logo和店名
    $.ajax({
        type: 'post',
        url: baseUrl+'index.jsp?control=Shop&method=findNameLogoById',
        dataType: 'json',
        data: {
            'clientJson': JSON.stringify(clientJson)
        },
        success: function (sJson) {
            $('#shopLogo').attr("src",sJson.shopLogo);
            $('#shopName').html(sJson.shopName);
        }
    });
    // 请求餐品列表
    $.ajax({
        type: 'post',
        url: baseUrl+'index.jsp?control=Food&method=foodList',
        dataType: 'json',
        data: {
            'clientJson': JSON.stringify(clientJson)
        },
        success: function (sJson) {
            var data = sJson.serverJson;
            for (var i=0; i<data.length; i++){
                var list = "<div class='list'>"+
                                "<img src='img/food.jpg' alt='商家logo' />"+
                                "<div class='list-wrap'>"+
                                    "<div class='list-part' onclick='foodDetail(\""+data[i].foodId+"\")'>"+
                                        "<p class='name'>"+data[i].foodName+"</p>"+
                                        "<p class='time'>"+data[i].foodPrice+"</p>"+
                                    "</div>"+
                                    "<div class='list-part'>"+
                                        "<p class='orderId'>"+data[i].foodDetail+"</p>"+
                                        "<button onclick='foodDelete(\""+data[i].foodId+"\")' class='btn btn-danger'>删除</button>"+
                                    "</div>"+
                                "</div>"+
                            "</div>";
                $('#list').append(list);
            }
        }
    });
});

function foodDelete(foodId) {
    var clientJson = new Object();
    clientJson.foodId = foodId;
    $.ajax({
        type: 'post',
        url: baseUrl+'index.jsp?control=Food&method=foodDelete',
        dataType: 'json',
        data: {
            clientJson: JSON.stringify(clientJson)
        },
        success: function (sJson) {
            alert(sJson.message);
            location.href='ShopFoodList.jsp';
        }
    });
}