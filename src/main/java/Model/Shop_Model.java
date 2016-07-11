package Model;

import core.DBUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Shop_Model {

    public Shop_Model() {
        System.out.println("Shop_Model");
    }

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
            DBUtil.close(conn);
            DBUtil.close(pst);
            DBUtil.close(rs);
        }

        return output;
    }

    public static JSONObject ShopSelect(String phone) {
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
            DBUtil.close(conn);
            DBUtil.close(pst);
            DBUtil.close(rs);
        }

        return sJson;
    }

    public static boolean ShopRegister(String phone, String password, String name) {
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
            DBUtil.close(conn);
            DBUtil.close(pst);
        }
        return rs;
    }

    public static JSONObject ShopList() {
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
            DBUtil.close(conn);
            DBUtil.close(pst);
            DBUtil.close(rs);
        }
        return sJson;
    }
}
