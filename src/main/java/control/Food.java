package control;

import dao.Food_Model;
import net.sf.json.JSONObject;

/**
 * control: Food
 */
public class Food {
    /**查看餐品列表
     * method: foodList
     * @param cJson
     * @return
     */
    public JSONObject foodList(JSONObject cJson){

        String shopPhone = cJson.getString("shopPhone");
        return Food_Model.foodList(shopPhone);
    }

    /**查看餐品详情
     * method: foodDetail
     * @param cJson
     * @return
     */
    public JSONObject foodDetail(JSONObject cJson){

        String foodId = cJson.getString("foodId");
        return Food_Model.foodDetail(foodId);
    }


    public JSONObject foodDelete(JSONObject cJson){
        String foodId = cJson.getString("foodId");
        return Food_Model.foodDelete(foodId);
    }
}
