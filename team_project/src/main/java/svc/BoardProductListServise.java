package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductBoardDAO;
import vo.ProductBoardDTO;
import static db.JdbcUtil.*;

public class BoardProductListServise {
	
	
	public int getListCount() {
		System.out.println("BoardProductListServise - getListCount() 메서드 호출");

		int listCount = 0;
		
		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount =dao.selectListCount();
		
		close(con);
		
		System.out.println("BoardProductListServise - DAO에서 넘어온 listCount : " + listCount);

		return listCount;
	}

	public ArrayList<ProductBoardDTO> getList(int pageNum, int listLimit) {
		System.out.println("BoardProductListServise - getList() 메서드 호출");
		
		ArrayList<ProductBoardDTO> list = null;

		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		list =dao.selectList(pageNum, listLimit);
		
		close(con);
		
		System.out.println("WishlistService - DAO 파일에서 담아온 list : " + list);

		return list;
	
	}

}
