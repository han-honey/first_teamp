package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductBoardDAO;
import vo.ProductBoardDTO;
import static db.JdbcUtil.*;

public class AdminProductListServise {
	
	
	public int getListCount() {
		System.out.println("AdminProductListServise - getListCount() 메서드 호출");

		int listCount = 0;
		
		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount =dao.selectListCount();
		
		close(con);
		
		System.out.println("AdminProductListServise - DAO에서 넘어온 listCount : " + listCount);

		return listCount;
	}

	public ArrayList<ProductBoardDTO> getList(int pageNum, int listLimit) {
		System.out.println("adminProductListServise - getList() 메서드 호출");
		
		ArrayList<ProductBoardDTO> list = null;

		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		list =dao.selectList(pageNum, listLimit);
		
		close(con);
		
		return list;
	
	}

}
