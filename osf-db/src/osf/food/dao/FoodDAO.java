package osf.food.dao;

import java.util.List;

import osf.food.vo.FoodVO;

public interface FoodDAO {

	public List<FoodVO> selectFoodList();
	public FoodVO selectFood(FoodVO food);
	public boolean insertFood(FoodVO food);
	public boolean deleteFood(FoodVO food);
	public boolean updateFood(FoodVO food);
}
