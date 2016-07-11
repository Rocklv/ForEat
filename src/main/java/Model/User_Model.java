package Model;

import core.DBUtil;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_Model {

    public User_Model(){
        System.out.println("User_Model");
    }

    public static boolean isLogin(String phone,String password){
        boolean output = false;
        String sql = "select name from user where id=? and password=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        System.out.println(phone + " " + password);
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,phone);
            pst.setString(2,password);
            rs = pst.executeQuery();
            if (rs.next()){
                output = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
            DBUtil.close(pst);
            DBUtil.close(rs);
        }

        return output;
    }

    public static JSONObject UserSelect(String phone){
        JSONObject sJson = new JSONObject();
        String sql = "select id,name,address from user where id=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        System.out.println(phone);
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,phone);
            rs = pst.executeQuery();
            if (rs.next()){
                sJson.element("userPhone",rs.getString("id"));
                sJson.element("userName",rs.getString("name"));
                sJson.element("userAddress",rs.getString("address"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
            DBUtil.close(pst);
            DBUtil.close(rs);
        }

        return sJson;
    }

    public static boolean UserRegister(String phone,String password,String name,String address){

        String sql ="insert into user(`id`,`password`,`name`,`address`) values(?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        boolean rs = false;

        System.out.println(phone+" "+password+" "+name+" "+address);
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,phone);
            pst.setString(2,password);
            pst.setString(3,name);
            pst.setString(4,address);
            rs = pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
            DBUtil.close(pst);
        }
        return rs;
    }
}