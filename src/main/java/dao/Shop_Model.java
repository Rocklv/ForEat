package dao;

import core.DBUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * model: Shop_Model
 */
public class Shop_Model {

    public Shop_Model() {
        System.out.println("shop_Model");
    }

    /**商家登陆
     * method: isLogin()
     * @param phone
     * @param password
     * @return
     */
    public static boolean isLogin(String phone, String password) {
        boolean output = false;
        String sql = "select name from shop where id=? and password=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        System.out.println(phone + " " + password);
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, phone);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                output = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(pst);
            DBUtil.close(conn);
        }
        return output;
    }

    /**查询商家详情(根据商家的电话号)
     * method: shopSelect()
     * @param phone
     * @return
     */
    public static JSONObject shopSelect(String phone) {
        JSONObject sJson = new JSONObject();
        String sql = "select id,state,name,detail from shop where id=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        System.out.println(phone);
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, phone);
            rs = pst.executeQuery();
            if (rs.next()) {
                sJson.element("shopPhone", rs.getString("id"));
                sJson.element("shopState", rs.getString("state"));
                sJson.element("shopName", rs.getString("name"));
                sJson.element("shopDetail", rs.getString("detail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(pst);
            DBUtil.close(conn);
        }
        return sJson;
    }

    /**商家注册
     * method: shopRegister
     * @param phone
     * @param password
     * @param name
     * @return
     */
    public static boolean shopRegister(String phone, String password, String name) {
        String sql = "insert into shop(`id`,`password`,`name`) values(?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        boolean rs = false;

        System.out.println(phone + " " + password + " " + name);
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, phone);
            pst.setString(2, password);
            pst.setString(3, name);

            rs = pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pst);
            DBUtil.close(conn);
        }
        return rs;
    }


    /**商家列表
     * method:shopList()
     * @return sJson
     */
    public static JSONObject shopList() {
        JSONObject sJson = new JSONObject();
        JSONArray rsJson = new JSONArray();
        JSONObject dataJson = new JSONObject();
        String sql = "select id,name from shop";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dataJson.element("shopPhone", rs.getString("id"));
                dataJson.element("shopName", rs.getString("name"));
                rsJson.add(dataJson);

                System.out.println("shopPhone:" + dataJson.getString("shopPhone") + " shopName:" + dataJson.getString("shopName"));
            }
            sJson.element("serverJson", rsJson);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(pst);
            DBUtil.close(conn);
        }
        return sJson;
    }

    /**查询商店名称和logo(根据商家手机号)
     * method: findNameLogoById()
     * @param shopId
     * @return
     */
    public static JSONObject findNameLogoById(String shopId){
        JSONObject sJson = new JSONObject();
        String sql = "select logo,name from shop where id=?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet res = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,shopId);
            res = pst.executeQuery();
            while (res.next()){
                sJson.element("shopLogo",res.getString("logo"));
                sJson.element("shopName",res.getString("name"));
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
