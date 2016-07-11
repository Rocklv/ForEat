package core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static String url = "jdbc:mysql://localhost:3306/easyeat?useUnicode=true&characterEncoding=utf-8";
	public static String name = "com.mysql.jdbc.Driver";
	public static String user = "root";
	public static String psd ="";
	public static Connection conn = null;
	/*
	 * 连接数据库
	 */
	public static Connection getConnection(){
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, psd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	/*
	 * 关闭数据库，释放资源（利用反射原理，找到对应的关闭方法）
	 */
	public static void close(Object obj){
		if(obj != null){
			Class c = obj.getClass();
			Method m;
			try {
				m = c.getMethod("close");
				m.invoke(obj);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
