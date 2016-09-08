$(function () {
    $.ajax({
        type: 'post',
        url: baseUrl+'index.jsp?control=Orders&method=orderDetail',
        dataType: 'json',
        data:{
            clientJson: JSON.stringify(clientJson)
        },
        success: function (sJson) {

            $("#orderState").html(showState(sJson.orderState));
            $("#orderId").html(sJson.orderId);
            $("#createTime").html(sJson.createTime);
            $("#foodName").html(sJson.foodName);
            $("#shopPhone").html(sJson.shopPhone);
            $("#userPhone").html(sJson.userPhone);
            $("#foodDetail").html(sJson.foodDetail);
        }
    });
});

//订单状态显示
function showState(orderState) {
    switch (orderState){
        case '0':
            return "等待商家接单。。。";
            break;
        case '1':
            return "商家已接单！";
            break;
        case '2':
            return "订单完成！";
            break;
        default:
            break;
    }
}