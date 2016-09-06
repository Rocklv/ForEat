$(function () {
    $.ajax({
        type:'post',
        url: baseUrl+'index.jsp?control=Orders&method=shopOrderList',
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
                $('#list').append(list);
            }
        }
    });
});

//根据订单“状态”呈现不同的业务服务
//0 
//1 表示商家已接单
function getOrderState(data) {
    switch (data.state){
        case '0':
            return "<button onclick='orderReceive(\""+data.orderId+"\")' class='btn-confirm'>接单</button>";
            break;
        case '1':
            return "<button class='btn-info'>任务中</button>";
            break;
        default:
            break;
    }
}

//商家接单
function orderReceive(orderId) {
    var clientJson = new Object();
    clientJson.orderId = orderId;
    $.ajax({
        type: 'post',
        url: baseUrl+"index.jsp?control=Orders&method=orderReceive",
        dataType: 'json',
        data: {
            'clientJson':JSON.stringify(clientJson)
        },
        success: function (sJson) {
            alert(sJson.message);
            location.href="ShopOrderList.jsp";
        }
    });
}