package core;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyReflect {

	public static void ReflectControl(String controller, String method, JSONObject clientJson, HttpServletResponse response) {

		JSONObject serverJson = null;
		if (!"".equals(controller) && !"".equals(method)) {

			System.out.println(controller+" "+method);
			Class<?> c;
			try {
				// 获取到控制器的类类型
				c = Class.forName("control." + controller);

				// 以该类型初始化一个新的对象（notice：比较耗费系统资源，也可以考虑单例模式）
				Object obj = c.newInstance();

				// 获取该类型对应的控制器的方法 并传入参数类型
				Method m = c.getMethod(method, new Class<?>[] {JSONObject.class});

				// 反射执行该方法
				System.out.println(m.toString());
				serverJson = (JSONObject) m.invoke( obj, clientJson);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
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
			} catch (InstantiationException e) {
				e.printStackTrace();
			}

			//将返回的json数据放入response对象中，用于响应客户端
			try {
				PrintWriter out = response.getWriter();
				out.write(serverJson.toString());
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
