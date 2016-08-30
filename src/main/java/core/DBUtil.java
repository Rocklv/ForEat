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

	/**
	 * method：getConnection()
	 * for get connection
	 * @return conn
     */
	public static Connection getConnection(){
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, psd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * method: close()
	 * for closing database
	 * @param obj
     */
	public static void close(Object obj){
		if(obj != null){
			Class c = obj.getClass();
			Method m;
			try {
				m = c.getMethod("close");
				m.invoke(obj);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
	}
}
