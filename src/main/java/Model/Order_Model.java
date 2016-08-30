package Model;

import core.DBUtil;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * model: Order_Model
 */
public class Order_Model {

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
}
