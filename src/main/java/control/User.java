package control;

import dao.User_Model;
import net.sf.json.JSONObject;

/**
 * control: User
 */

public class User {

	public String resultCode = "resultCode";
	public String resultMessage = "resultMessage";
	public User() {
	}

	/**用户登录
	 * method: UserLogin()
	 * @param cJson 客户端发送的数据
	 * @return sJson 服务端响应的数据
     */
	public JSONObject userLogin(JSONObject cJson) {
		JSONObject sJson = new JSONObject();

		String phone = cJson.getString("phone");
		String password = cJson.getString("password");

		//用户登录验证
		boolean userIsLogin = User_Model.isLogin(phone,password);

		if(userIsLogin){
			sJson.element(resultCode, 0);
			sJson.element(resultMessage, "用户登陆成功");
		}else {
			sJson.element(resultCode, 1);
			sJson.element(resultMessage, "用户登陆失败");
		}
		return sJson;
	}

	/**用户中心
	 * method: userCenter
	 * @param cJson
	 * @return
     */
	public JSONObject userCenter(JSONObject cJson){
		JSONObject sJson;

		String phone = cJson.getString("userPhone");

		sJson = User_Model.userSelect(phone);

		System.out.println(sJson.getString("userPhone")+
							sJson.getString("userName")+
							sJson.getString("userAddress"));
		return sJson;
	}

	/**用户注册
	 * method: userRegister()
	 * @param cJson
	 * @return
     */
	public JSONObject userRegister(JSONObject cJson){
		JSONObject sJson = new JSONObject();

		String userPhone = cJson.getString("userPhone");
		String userPassword = cJson.getString("userPassword");
		String userName = cJson.getString("userName");
		String userAddress = cJson.getString("userAddress");

		boolean userIsReg = User_Model.userRegister(userPhone,userPassword,userName,userAddress);

		if (userIsReg){
			sJson.element(resultCode,0);
			sJson.element(resultMessage,"用户注册成功！");
		}else {
			sJson.element(resultCode,1);
			sJson.element(resultMessage,"用户注册失败！");
		}

		return sJson;
	}
}
