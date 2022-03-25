package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardDTO;


public class BoardNoticeListService {

	public int getListCount() {
		System.out.println("BoardListService - getListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		
		BoardDAO noticeBoardDAO = BoardDAO.getInstance();		
		
		noticeBoardDAO.setConnection(con);
		
		listCount = noticeBoardDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}
	
	public ArrayList<BoardDTO> getArticleList(int pageNum, int listLimit) {
		System.out.println("BoardListService - getArticleList()");
		ArrayList<BoardDTO> articleList = null;
		
		Connection con = getConnection();
		
		BoardDAO boardDAO = BoardDAO.getInstance();		
		
		boardDAO.setConnection(con);
		
		articleList = boardDAO.selectArticleList(pageNum, listLimit);
		
		close(con);
		
		return articleList;
	}
}
