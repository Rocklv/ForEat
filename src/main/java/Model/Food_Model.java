package Model;

import core.DBUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Food_Model {
    public static JSONObject UserFoodList(String shopPhone){
        JSONObject sJson = new JSONObject();
        JSONArray rsJson = new JSONArray();
        JSONObject dataJson = new JSONObject();
        String sql = "select * from food,shop where food.shop_id=shop.id and shop.id=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,shopPhone);
            rs = pst.executeQuery();
            if (rs.next()){
                dataJson.element("foodId",rs.getString("food.id"));
                dataJson.element("shopId",rs.getString("food.shop_id"));
                dataJson.element("foodName",rs.getString("food.name"));
                dataJson.element("foodDetail",rs.getString("food.detail"));
                dataJson.element("foodPrice",rs.getString("food.price"));
                dataJson.element("shopName",rs.getString("shop.name"));
                rsJson.add(dataJson);
            }
            sJson.element("serverJson", rsJson);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
            DBUtil.close(pst);
            DBUtil.close(rs);
        }

        return sJson;
    }
}
