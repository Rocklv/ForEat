package control;


import Model.User_Model;
import net.sf.json.JSONObject;

import javax.servlet.http.*;

public class User {

	public String resultCode = "resultCode";
	public String resultMessage = "resultMessage";
	public User() {
	}

	/**
	 * method: UserLogin
	 * @param cJson 客户端发送的数据
	 * @return sJson 服务端响应的数据
     */
	public JSONObject UserLogin(JSONObject cJson) {
		JSONObject sJson = new JSONObject();

		String phone = cJson.getString("phone");
		String password = cJson.getString("password");

		//用户登录验证
		boolean isLogin = User_Model.isLogin(phone,password);

		if(isLogin){
			sJson.element(resultCode, 0);
			sJson.element(resultMessage, "登陆成功");
		}else {
			sJson.element(resultCode, 1);
			sJson.element(resultMessage, "登陆失败");
		}
		return sJson;
	}

	/**
	 * method: UserCenter
	 */
	public JSONObject UserCenter(JSONObject cJson){
		JSONObject sJson;

		String phone = cJson.getString("userPhone");

		sJson = User_Model.UserSelect(phone);

		System.out.println(sJson.getString("userPhone")+
							sJson.getString("userName")+
							sJson.getString("userAddress"));
		return sJson;
	}

	/**
	 * method: UserRegister
	 */
	public JSONObject UserRegister(JSONObject cJson){
		JSONObject sJson = new JSONObject();

		String userPhone = cJson.getString("userPhone");
		String userPassword = cJson.getString("userPassword");
		String userName = cJson.getString("userName");
		String userAddress = cJson.getString("userAddress");

		boolean isReg = User_Model.UserRegister(userPhone,userPassword,userName,userAddress);

		if (isReg){
			sJson.element(resultCode,0);
			sJson.element(resultMessage,"注册成功！");
		}else {
			sJson.element(resultCode,1);
			sJson.element(resultMessage,"注册失败！");
		}

		return sJson;
	}
}
