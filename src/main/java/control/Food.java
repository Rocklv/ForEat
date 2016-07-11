package control;

import Model.Food_Model;
import net.sf.json.JSONObject;

/**
 * Created by Rocklv on 2016/7/8.
 */
public class Food {
    /**
     * method: UserFoodList
     */
    public JSONObject UserFoodList(JSONObject cJson){
        JSONObject sJson;
        String shopPhone = cJson.getString("shopPhone");

        sJson = Food_Model.UserFoodList(shopPhone);

        return sJson;
    }
}
