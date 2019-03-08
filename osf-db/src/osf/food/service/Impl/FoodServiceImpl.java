package osf.food.service.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import osf.food.dao.FoodDAO;
import osf.food.dao.impl.FoodDAOImpl;
import osf.food.db.DBCon;
import osf.food.service.FoodService;
import osf.food.vo.FoodVO;

public class FoodServiceImpl implements FoodService {

	private FoodDAO fdao = new FoodDAOImpl();
	
	@Override
	public List<FoodVO> selectFoodList(FoodVO sFood) {
		return fdao.selectFoodList(sFood);
	}

	@Override
	public FoodVO selectFood(Integer foodNum) {
		return fdao.selectFood(foodNum);
	}

	@Override
	public int insertFood(FoodVO food) throws Exception { //델리데이션 : 유효성검사//인터페이스에도 똑같이 throws 조건이 있어야한다
		List<FoodVO> tmpFoodList = fdao.selectFoodList(food);
		if(tmpFoodList.size() != 0) {
			throw new Exception("중복!!!!!");
		}
		return fdao.insertFood(food);
	}

	@Override
	public int deleteFood(FoodVO food) throws Exception {
		FoodVO tmpFood = fdao.selectFood(food.getFoodNum());
		if(tmpFood == null) {
			throw new Exception("없는 음식입니다.");
		}
		return fdao.deleteFood(food);
	}

	@Override
	public int updateFood(FoodVO food) throws Exception {
		List<FoodVO> tmpFoodList = fdao.selectFoodList(food);
		if(tmpFoodList.size() != 0) {
			throw new Exception("이미 가지고 있는 메뉴 이름입니다.");
		}
		return fdao.updateFood(food);
	}
	
	public static void main(String[] args) {
		FoodService fs = new FoodServiceImpl();
		List<FoodVO> foodList = fs.selectFoodList(null);
		System.out.println(foodList);
		FoodVO food = new FoodVO();
		food.setFoodName("참치");
		foodList = fs.selectFoodList(food);
		System.out.println(foodList);
		
//		food.setFoodName("동원참치");
//		food.setFoodPrice(1800);
//		int cnt=0;
//		try {
//			cnt = fs.insertFood(food);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("저장 건수 : " + cnt);
		
//		food.setFoodNum(7);
//		food.setFoodName("동원참치");
//		food.setFoodPrice(30050);
//		int cnt = 0;
//		try {
//			cnt = fs.updateFood(food);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("수정 건수 : " + cnt);
		
		food.setFoodNum(7);
		int cnt = 0;
		try {
			cnt = fs.deleteFood(food);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("삭제 건수 : " + cnt);
		
	}

}
