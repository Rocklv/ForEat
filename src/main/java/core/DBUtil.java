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
	 * �������ݿ�
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
	 * �ر����ݿ⣬�ͷ���Դ�����÷���ԭ���ҵ���Ӧ�Ĺرշ�����
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
