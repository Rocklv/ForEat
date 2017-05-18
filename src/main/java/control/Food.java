package control;

import dao.FoodDao;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        String sql = "select * from food,shop where food.shop_id=shop.id and shop.id=?";
        return FoodDao.foodList(shopPhone,sql);
    }

    /**查看餐品详情
     * method: foodDetail
     * @param cJson
     * @return
     */
    public JSONObject foodDetail(JSONObject cJson){

        String foodId = cJson.getString("foodId");
        String sql = "select shop_id,food.name,food.detail,shop.name,food.pic from food,shop " +
                "where shop.id=(select shop_id from food where food.id=?) and food.id=?";
        return FoodDao.foodDetail(foodId,sql);
    }

    /**删除餐品
     * method: foodDelete
     * @param cJson
     * @return
     */
    public JSONObject foodDelete(JSONObject cJson){

        String foodId = cJson.getString("foodId");
        String sql = "delete from food where id=?";
        return FoodDao.foodDelete(foodId,sql);
    }

    /**增加餐品
     * method: foodAdd
     * @param cJson
     * @return
     */
    public JSONObject foodAdd(JSONObject cJson){

        String id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+cJson.getString("shopId");
        cJson.element("foodId",id);
        String sql = "insert into food values(?,?,?,?,?,?)";
        return FoodDao.foodAdd(cJson,sql);
    }
}
