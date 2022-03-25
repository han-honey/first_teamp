package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;
import vo.ReviewDTO;

import static db.JdbcUtil.*;

public class ReviewListService {

	public int getListCount(String product_code) {
		System.out.println("ReviewListService - getListCount()");

		int listCount = 0;
		Connection con =getConnection();
		ReviewDAO dao = ReviewDAO.getInstance();
		dao.setConnection(con);
		listCount = dao.selectListCount(product_code);
		close(con);
		return listCount;
	}

	
	public ArrayList<ReviewDTO> getArticleList(int pageNum, int listLimit ,String product_code) {
		System.out.println("ReviewListService - getArticleList()");
		
		ArrayList<ReviewDTO> aticleList = null;
		
		Connection con = getConnection();
		ReviewDAO dao = ReviewDAO.getInstance();
		dao.setConnection(con);
		aticleList = dao.selectArticeList(pageNum,listLimit,product_code);
		close(con);
		return aticleList;
	}
	
	public double starScope(String product_code) {
		
		double scope = 0.0;
		Connection con =getConnection();
		ReviewDAO dao = ReviewDAO.getInstance();
		dao.setConnection(con);
		scope = dao.starScope(product_code);
		
		close(con);
		
		return scope;
	}
	
	public int reviewCount(String product_code) {
		System.out.println("ReviewListService - reviewCount");
		
		int reviewCount = 0;
		
		Connection con = getConnection();
		ReviewDAO dao = ReviewDAO.getInstance();
		dao.setConnection(con);
		reviewCount = dao.reviewCount(product_code);
		close(con);
		return reviewCount;
	}
	
}
