package control;

import Model.Shop_Model;
import net.sf.json.JSONObject;

/**
 * Created by Rocklv on 2016/7/7.
 */
public class Shop {

    public String resultCode = "resultCode";
    public String resultMessage = "resultMessage";
    public Shop(){
    }

    /**
     * method: ShopLogin
     * @param cJson 客户端发送的数据
     * @return sJson 服务端响应的数据
     */
    public JSONObject ShopLogin(JSONObject cJson) {
        JSONObject sJson = new JSONObject();

        String phone = cJson.getString("phone");
        String password = cJson.getString("password");

        //用户登录验证
        boolean isLogin = Shop_Model.isLogin(phone,password);

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
     * method: ShopCenter
     */
    public JSONObject ShopCenter(JSONObject cJson){
        JSONObject sJson;

        String phone = cJson.getString("shopPhone");

        sJson = Shop_Model.ShopSelect(phone);

        System.out.println(sJson.getString("shopPhone")+
                sJson.getString("shopName")+
                sJson.getString("shopState"));
        return sJson;
    }

    /**
     * method: ShopRegister
     */
    public JSONObject ShopRegister(JSONObject cJson){
        JSONObject sJson = new JSONObject();

        String shopPhone = cJson.getString("shopPhone");
        String shopName = cJson.getString("shopName");
        String shopPassword = cJson.getString("shopPassword");

        boolean isReg = Shop_Model.ShopRegister(shopPhone,shopPassword,shopName);

        if (isReg){
            sJson.element(resultCode,0);
            sJson.element(resultMessage,"注册成功！");
        }else {
            sJson.element(resultCode,1);
            sJson.element(resultMessage,"注册失败！");
        }

        return sJson;
    }

    /**
     * method: ShopList
     */
    public JSONObject ShopList(JSONObject cJson){

        return Shop_Model.ShopList();
    }
}
