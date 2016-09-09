package control;

import dao.Order_Model;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**控制器：订单
 * control: Orders
 */
public class Orders {

    /**用户下单
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

    /**用户查看订单列表
     * method: userOrderList()
     * @param cJson
     * @return
     */
    public JSONObject userOrderList(JSONObject cJson){

        String userId = (String) cJson.get("userPhone");
        //sql算法
        String sql = "SELECT orders.id, food.name, createTime, state " +
                        "FROM orders,food " +
                        "WHERE orders.food_id=food.id " +
                        "and user_id=?";
        return Order_Model.orderList(userId,sql);
    }

    /**商家查看订单列表
     * method: shopOrderList()
     * @param cJson
     * @return
     */
    public JSONObject shopOrderList(JSONObject cJson){

        String shopId = (String) cJson.get("shopPhone");
        //sql算法
        String sql = "SELECT orders.id, food.name, createTime, state " +
                    "FROM orders,food " +
                    "WHERE orders.food_id=food.id " +
                    "and orders.state in('0','1') " +
                    "and orders.shop_id=?";
        return Order_Model.orderList(shopId,sql);
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

    /**商家接单
     * method: orderReceive()
     * @param cJson
     * @return
     */
    public JSONObject orderReceive(JSONObject cJson){

        String orderId = (String) cJson.get("orderId");
        return Order_Model.orderReceive(orderId);
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
