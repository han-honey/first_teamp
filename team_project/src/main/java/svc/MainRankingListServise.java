package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductBoardDAO;
import vo.ProductBoardDTO;
import static db.JdbcUtil.*;

public class MainRankingListServise {

	// 최근순
	public ArrayList<ProductBoardDTO> newRankingList() {
		
		System.out.println("MainRankingListServise - newRankingList() 메서드 호출");
		ArrayList<ProductBoardDTO> newRankingList = null;
		
		Connection con = getConnection();
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		dao.setConnection(con);
		newRankingList = dao.selectNewRankingList();
		
		close(con);
		
		System.out.println("MainRankingListServise - DAO 파일에서 담아온 newRankingList : " + newRankingList);
		
		return newRankingList;
	}
	
	
	// 최다판매순
	public ArrayList<ProductBoardDTO> sellRankingList() {
		
		System.out.println("MainRankingListServise - sellRankingList() 메서드 호출");
		
		ArrayList<ProductBoardDTO> sellRankingList = null;
		
		Connection con = getConnection();
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		dao.setConnection(con);
		sellRankingList = dao.selectSellRankingList();
		
		close(con);
		
		System.out.println("MainRankingListServise - DAO 파일에서 담아온 sellRankingList : " + sellRankingList);
		
		return sellRankingList;
	}
	
	// 평점순
	public ArrayList<ProductBoardDTO> starScoreRankingList() {
		
		System.out.println("MainRankingListServise - starScoreRankingList() 메서드 호출");
		
		ArrayList<ProductBoardDTO> starScoreRankingList = null;
		
		Connection con = getConnection();
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		dao.setConnection(con);
		starScoreRankingList = dao.selectStarScoreRankingList();
		
		close(con);
		
		System.out.println("MainRankingListServise - DAO 파일에서 담아온 starScoreRankingList : " + starScoreRankingList);
		
		return starScoreRankingList;
	}

}
