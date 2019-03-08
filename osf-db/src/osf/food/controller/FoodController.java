package osf.food.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import osf.food.db.DBCon;
import osf.food.service.FoodService;
import osf.food.service.Impl.FoodServiceImpl;
import osf.food.vo.FoodVO;

public class FoodController {

	private FoodService fs = new FoodServiceImpl();

	public void doGet(Map<String, String> req, Map<String, Object> res) throws Exception {
		String cmd = req.get("cmd");
		String foodName = req.get("foodName");
		FoodVO food = new FoodVO();
		food.setFoodName(foodName);
		if ("list".equals(cmd)) {
			res.put("list", fs.selectFoodList(food));
		} else if ("food".equals(cmd)) {
			String foodNum = req.get("foodNum");
			res.put("food", fs.selectFood(Integer.parseInt(foodNum)));
		} else {
			throw new Exception("요청하신 서비스는 없는 서비스 입니다.");
		}
	}

	public void doPost(Map<String, String> req, Map<String, Object> res) throws Exception {
		String cmd = req.get("cmd");
		String foodNum = req.get("foodNum");
		String foodName = req.get("foodName");
		String foodPrice = req.get("foodPrice");
		FoodVO food = new FoodVO();
		
		
		
		if ("insert".equals(cmd)) {
			food.setFoodName(foodName);
			food.setFoodPrice(Integer.parseInt(foodPrice));
			res.put("insert", fs.insertFood(food));
		} else if ("update".equals(cmd)) {
			food.setFoodNum(Integer.parseInt(foodNum));
			food.setFoodName(foodName);
			food.setFoodPrice(Integer.parseInt(foodPrice));
			res.put("update",fs.updateFood(food));
		} else if ("delete".equals(cmd)) {
			food.setFoodNum(Integer.parseInt(foodNum));
			res.put("delete",fs.deleteFood(food));
		} else {

		}
	}
}
