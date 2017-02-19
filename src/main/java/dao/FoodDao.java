package dao;

import core.DBUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.processors.JsonBeanProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * model: FoodDao
 */
public class FoodDao {

    /**用户查询食品列表
     * method: userFoodList()
     * @param shopPhone
     * @return
     */
    public static JSONObject foodList(String shopPhone,String sql){
        JSONObject sJson = new JSONObject();
        JSONArray arrJson = new JSONArray();
        JSONObject dataJson = new JSONObject();

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
                dataJson.element("foodLogo",rs.getString("food.pic"));
                arrJson.add(dataJson);
            }
            sJson.element("serverJson", arrJson);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(pst);
            DBUtil.close(conn);
        }

        return sJson;
    }

    /**餐品详情
     * method: foodDetail()
     * @param foodId
     * @return
     */
    public static JSONObject foodDetail(String foodId,String sql){
        JSONObject sJson = new JSONObject();
        JSONObject dataJson = new JSONObject();

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

    /**删除餐品
     * method: foodDelete()
     * @param foodId
     * @return
     */
    public static JSONObject foodDelete(String foodId,String sql){
        JSONObject sJson = new JSONObject();
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = null;
        int res;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,foodId);
            res = pst.executeUpdate();
            if (res != 1)
                sJson.element("message","删除失败，请重新操作！");
            else
                sJson.element("message","删除成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(pst);
            DBUtil.close(con);
        }
        return sJson;
    }

    public static JSONObject foodAdd(JSONObject cJson,String sql){
        JSONObject sJson = new JSONObject();
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = null;
        int res;

        try {
            ps = con.prepareStatement(sql);
            //填充数据
            ps.setString(1,cJson.getString("foodId"));
            ps.setString(2,cJson.getString("shopId"));
            ps.setString(3,cJson.getString("foodName"));
            ps.setString(4,cJson.getString("foodDetail"));
            ps.setString(5,cJson.getString("foodPrice"));
            ps.setString(6,cJson.getString("foodPic"));
            res = ps.executeUpdate();
            if (res != 1)
                sJson.element("message","添加失败！");
            else
                sJson.element("message","添加成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(ps);
            DBUtil.close(con);
        }
        return sJson;
    }
}
