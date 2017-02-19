package dao;

import core.DBUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**数据处理：订单
 * model: OrderDao
 */
public class OrderDao {

    /**用户下单
     * method：orderAdd
     * @param id
     * @param userId
     * @param shopId
     * @param foodId
     * @param createTime
     * @return
     */
    public static JSONObject orderAdd(String id,String userId,String shopId,String foodId,String createTime){

        JSONObject sJson = new JSONObject();
        String sql = "insert into orders(id,user_id,shop_id,food_id,createTime) values(?,?,?,?,?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet res = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,id);
            pst.setString(2,userId);
            pst.setString(3,shopId);
            pst.setString(4,foodId);
            pst.setString(5,createTime);

            if (pst.executeUpdate() > 0){
                sJson.put("message","已下单！");
            }else {
                sJson.put("message","下单失败，请重新下单！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(res);
            DBUtil.close(pst);
            DBUtil.close(con);
        }
        return sJson;
    }

    /**查看订单列表
     * method: orderList()
     * @param roleId
     * @param sql
     * @return
     */
    public static JSONObject orderList(String roleId,String sql){
        //初始化json数据结构
        JSONObject sJson = new JSONObject();
        JSONArray arrJson = new JSONArray();
        JSONObject dataJson = new JSONObject();

        Connection con = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet res = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,roleId);
            res = pst.executeQuery();
            while (res.next()){
                dataJson.element("orderId",res.getString("id"));
                dataJson.element("foodName",res.getString("name"));
                dataJson.element("createTime",res.getString("createTime"));
                dataJson.element("state",res.getString("state"));
                arrJson.add(dataJson);
            }
            sJson.element("serverJson",arrJson);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(res);
            DBUtil.close(pst);
            DBUtil.close(con);
        }
        return sJson;
    }

    /**订单详情
     * method: orderDetail
     * @return
     */
    public static JSONObject orderDetail(String orderId){
        JSONObject sJson = new JSONObject();
        String sql = "SELECT orders.state,orders.id,orders.createTime,food.name,orders.shop_id,orders.user_id,food.detail " +
                        "FROM orders,food WHERE orders.food_id=food.id and orders.id=?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet res = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,orderId);
            res = pst.executeQuery();

            while (res.next()){
                sJson.element("orderState",res.getString("orders.state"));
                sJson.element("orderId",res.getString("orders.id"));
                sJson.element("createTime",res.getString("orders.createTime"));
                sJson.element("foodName",res.getString("food.name"));
                sJson.element("shopPhone",res.getString("orders.shop_id"));
                sJson.element("userPhone",res.getString("orders.user_id"));
                sJson.element("foodDetail",res.getString("food.detail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(res);
            DBUtil.close(pst);
            DBUtil.close(con);
        }
        return sJson;
    }

    /**确认收货
     * method: orderConfirm
     * @param orderId
     * @return
     */
    public static JSONObject orderConfirm(String orderId){
        JSONObject sJson = new JSONObject();
        String sql = "UPDATE orders SET state='2' WHERE id=?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = null;
        int res;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,orderId);
            res = pst.executeUpdate();
            if (res==1)
                sJson.element("message","已确认收获！");
            else
                sJson.element("message","请重新操作！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(pst);
            DBUtil.close(con);
        }
        return sJson;
    }

    /**订单删除
     *method: orderDelete()
     * @param orderId
     * @return
     */
    public static JSONObject orderDelete(String orderId){
        JSONObject sJson = new JSONObject();
        String sql = "delete from orders where id=?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = null;
        int res;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,orderId);
            res = pst.executeUpdate();
            if (res==1)
                sJson.element("message","订单删除成功！");
            else
                sJson.element("message","操作失败！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(pst);
            DBUtil.close(con);
        }
        return sJson;
    }

    /**商家接单
     * method: orderReceive()
     * @param orderId
     * @return
     */
    public static JSONObject orderReceive(String orderId){
        JSONObject sJson = new JSONObject();
        String sql = "UPDATE orders SET state='1' WHERE id=?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = null;
        int res;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,orderId);
            res = pst.executeUpdate();
            if (res==1)
                sJson.element("message","接单成功！");
            else
                sJson.element("message","接单失败！请重新操作");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(pst);
            DBUtil.close(con);
        }
        return sJson;
    }
}