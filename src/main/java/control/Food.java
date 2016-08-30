package control;

import Model.Food_Model;
import net.sf.json.JSONObject;

/**
 * control: Food
 */
public class Food {
    /**
     * method: userFoodList
     */
    public JSONObject userFoodList(JSONObject cJson){

        String shopPhone = cJson.getString("shopPhone");
        return Food_Model.userFoodList(shopPhone);
    }

    /**
     * method: foodDetail
     * @param cJson
     * @return
     */
    public JSONObject foodDetail(JSONObject cJson){

        String foodId = cJson.getString("foodId");
        return Food_Model.foodDetail(foodId);
    }
}
