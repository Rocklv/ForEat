package control;

import dao.Shop_Model;
import net.sf.json.JSONObject;

/**
 * control: Shop
 */
public class Shop {

    public String resultCode = "resultCode";
    public String resultMessage = "resultMessage";
    public Shop(){
    }

    /**商家登陆
     * method: shopLogin()
     * @param cJson 客户端发送的数据
     * @return sJson 服务端响应的数据
     */
    public JSONObject shopLogin(JSONObject cJson) {
        JSONObject sJson = new JSONObject();

        String phone = cJson.getString("phone");
        String password = cJson.getString("password");

        //商家登录验证
        boolean shopIsLogin = Shop_Model.isLogin(phone,password);

        if(shopIsLogin){
            sJson.element(resultCode, 0);
            sJson.element(resultMessage, "商家登陆成功");
        }else {
            sJson.element(resultCode, 1);
            sJson.element(resultMessage, "商家登陆失败");
        }
        return sJson;
    }

    /**商家中心
     * method: shopCenter()
     * @param cJson
     * @return
     */
    public JSONObject shopCenter(JSONObject cJson){
        JSONObject sJson;

        String phone = cJson.getString("shopPhone");

        sJson = Shop_Model.shopSelect(phone);

        return sJson;
    }

    /**商家注册
     * method: shopRegister()
     * @param cJson
     * @return
     */
    public JSONObject shopRegister(JSONObject cJson){
        JSONObject sJson = new JSONObject();

        String shopPhone = cJson.getString("shopPhone");
        String shopName = cJson.getString("shopName");
        String shopPassword = cJson.getString("shopPassword");

        boolean shopIsReg = Shop_Model.shopRegister(shopPhone,shopPassword,shopName);

        if (shopIsReg){
            sJson.element(resultCode,0);
            sJson.element(resultMessage,"商家注册成功！");
        }else {
            sJson.element(resultCode,1);
            sJson.element(resultMessage,"商家注册失败！");
        }

        return sJson;
    }

    /**商家列表
     * method: shopList()
     * @param cJson
     * @return
     */
    public JSONObject shopList(JSONObject cJson){

        return Shop_Model.shopList();
    }

    public JSONObject findNameLogoById(JSONObject cJson){

        String shopId = cJson.getString("shopPhone");
        return Shop_Model.findNameLogoById(shopId);
    }
}
