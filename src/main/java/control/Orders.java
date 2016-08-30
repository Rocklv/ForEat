package control;

import Model.Order_Model;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * control: Orders
 */
public class Orders {

    /**
     * method: orderAdd
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
}
