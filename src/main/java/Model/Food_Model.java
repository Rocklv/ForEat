package Model;

import core.DBUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * model: Food_Model
 */
public class Food_Model {

    /**用户查询食品列表
     * method: userFoodList()
     * @param shopPhone
     * @return
     */
    public static JSONObject userFoodList(String shopPhone){
        JSONObject sJson = new JSONObject();
        JSONArray arrJson = new JSONArray();
        JSONObject dataJson = new JSONObject();
        String sql = "select * from food,shop where food.shop_id=shop.id and shop.id=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,shopPhone);
            rs = pst.executeQuery();
            while (rs.next()){
                dataJson.element("foodId",rs.getString("food.id"));
                dataJson.element("shopId",rs.getString("food.shop_id"));
                dataJson.element("foodName",rs.getString("food.name"));
                dataJson.element("foodDetail",rs.getString("food.detail"));
                dataJson.element("foodPrice",rs.getString("food.price"));
                dataJson.element("shopName",rs.getString("shop.name"));
                arrJson.add(dataJson);
            }
            sJson.element("serverJson", arrJson);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
            DBUtil.close(pst);
            DBUtil.close(rs);
        }

        return sJson;
    }

    /**用户查询餐品详情
     * method：FoodDetail()
     */
    public static JSONObject foodDetail(String foodId){
        JSONObject sJson = new JSONObject();
        JSONObject dataJson = new JSONObject();
        String sql = "select shop_id,food.name,food.detail,shop.name from food,shop " +
                     "where shop.id=(select shop_id from food where food.id=?) and food.id=?";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,foodId);
            pst.setString(2,foodId);
            rs = pst.executeQuery();
            while (rs.next()){
                dataJson.element("shopId",rs.getString("food.shop_id"));
                dataJson.element("foodName",rs.getString("food.name"));
                dataJson.element("foodDetail",rs.getString("food.detail"));
                dataJson.element("shopName",rs.getString("shop.name"));
            }
            sJson.element("serverJson",dataJson);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(pst);
            DBUtil.close(conn);
        }

        return sJson;
    }
}
