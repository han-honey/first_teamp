package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardEventDAO;
import vo.BoardEventDTO;


public class BoardEventListService {

	public int getListCount() {
		System.out.println("BoardEventListService - getListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		
		BoardEventDAO boardEventDAO = BoardEventDAO.getInstance();		
		
		boardEventDAO.setConnection(con);
		
		listCount = boardEventDAO.selectEventListCount();
		
		close(con);
		
		return listCount;
	}

	public ArrayList<BoardEventDTO> getArticleList(int pageNum, int listLimit) {
		System.out.println("BoardEventListService - getArticleList()");
		ArrayList<BoardEventDTO> articleList = null;
		
		Connection con = getConnection();
		
		BoardEventDAO boardEventDAO = BoardEventDAO.getInstance();		
		
		boardEventDAO.setConnection(con);
		
		articleList = boardEventDAO.selectEventArticleList(pageNum, listLimit);
		
		close(con);
		
		return articleList;
	}

}
