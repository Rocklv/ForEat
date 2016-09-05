package control;

import Model.Order_Model;
import net.sf.json.JSONObject;

import javax.print.attribute.standard.JobSheets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * control: Orders
 */
public class Orders {

    /**下单
     * method: orderAdd()
     * @param cJson
     * @return
     */
    public JSONObject orderAdd(JSONObject cJson){

        String foodId = (String) cJson.get("foodId");
        String shopId = (String) cJson.get("shopId");
        String userId = (String) cJson.get("userId");

        //订单号
        String id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + userId;

        //订单生成时间
        String createTime = new SimpleDateFormat("yy年MM月dd日-HH:mm:ss").format(new Date());

        return Order_Model.orderAdd(id,userId,shopId,foodId,createTime);
    }

    /**订单列表
     * method: orderList()
     * @param cJson
     * @return
     */
    public JSONObject orderList(JSONObject cJson){

        String userId = (String) cJson.get("userPhone");
        return Order_Model.orderList(userId);
    }

    /**订单详情
     *method: orderDetail()
     * @param cJson
     * @return
     */
    public JSONObject orderDetail(JSONObject cJson){

        String orderId = (String) cJson.get("orderId");
        return Order_Model.orderDetail(orderId);
    }

    /**确认收货
     * method: orderConfirm()
     * @param cJson
     * @return
     */
    public JSONObject orderConfirm(JSONObject cJson){

        String orderId = (String) cJson.get("orderId");
        return  Order_Model.orderConfirm(orderId);
    }

    /**订单删除
     * method: orderDelete()
     * @param cJson
     * @return
     */
    public JSONObject orderDelete(JSONObject cJson){

        String orderId = (String) cJson.get("orderId");
        return Order_Model.orderDelete(orderId);
    }
}
