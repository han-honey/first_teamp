package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductBoardDAO;
import vo.ProductBoardDTO;
import static db.JdbcUtil.*;

public class BoardProductListService {
	// -----------------------------------------------------------------------
		// 전체 상품리스트 갯수 가져오기 - 홍범씨의 service 메서드
	public int getListCount() {
		System.out.println("BoardProductListService - getListCount() 메서드 호출");

		int listCount = 0;
		
		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount =dao.selectListCount();
		
		close(con);
		
		System.out.println("BoardProductListService - DAO에서 넘어온 listCount : " + listCount);

		return listCount;
	}
	// -----------------------------------------------------------------------
	// 전체 상품리스트 갯수 가져오기(페이징 처리)
	public int getAllListCount(String product_category1) {
		System.out.println("BoardProductListService - getListCount(전체) 메서드 호출");

		int listCount = 0;
		
		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount =dao.selectListCount(product_category1);
		
		close(con);
		
		System.out.println("BoardProductListService - DAO에서 넘어온 listCount : " + listCount);

		return listCount;
	}

	// 전체 상품리스트 가져오기 - 홍범씨의 메서드
	public ArrayList<ProductBoardDTO> getList(int pageNum, int listLimit) {
		System.out.println("BoardProductListService - getList() 메서드 호출");
		
		ArrayList<ProductBoardDTO> list = null;

		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		list =dao.selectList(pageNum, listLimit);
		
		close(con);
		
		System.out.println("WishlistService - DAO 파일에서 담아온 list : " + list);

		return list;
	
	}
	// 전체 상품리스트 가져오기
	public ArrayList<ProductBoardDTO> getList(int pageNum, int listLimit, String product_category1) {
		
		System.out.println("BoardProductListService - getList(전체) 메서드 호출");
		System.out.println(product_category1);
		ArrayList<ProductBoardDTO> list = null;
		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		dao.setConnection(con);
		list =dao.selectList(pageNum, listLimit, product_category1);
		
		close(con);
		
		System.out.println("BoardProductListService - DAO 파일에서 담아온 list : " + list);
		
		return list;
	
	}
	
	// -----------------------------------------------------------------------
	// 검색한 상품리스트 갯수 가져오기(페이징 처리)
	public int getSearchListCount(String searchProductName) {
		System.out.println("BoardProductListService - getListCount(검색) 메서드 호출");

		int listCount = 0;
		
		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount =dao.searchProductListCount(searchProductName);
		
		close(con);
		
		System.out.println("BoardProductListService - DAO에서 넘어온 listCount : " + listCount);

		return listCount;
	}
	
	// -----------------------------------------------------------------------
	// 검색한 상품리스트 가져오기
	public ArrayList<ProductBoardDTO> getList(String searchProductName, int pageNum, int listLimit) {
		
		System.out.println("BoardProductListService - getList(검색) 메서드 호출");
		
		ArrayList<ProductBoardDTO> list = null;
		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		dao.setConnection(con);
		list =dao.selectProductList(searchProductName, pageNum, listLimit);
		
		close(con);
		
		System.out.println("BoardProductListService - DAO 파일에서 담아온 list : " + list);
		
		return list;
	
	}

}
