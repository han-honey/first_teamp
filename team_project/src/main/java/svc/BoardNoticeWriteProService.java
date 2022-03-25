package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardDTO;

public class BoardNoticeWriteProService {
	public boolean registArticle(BoardDTO board) {
		System.out.println("BoardNoticeWriteProService - registArticle()");
		
		boolean isWriteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		int insertCount = boardDAO.insertBoard(board);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
			isWriteSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isWriteSuccess;
	}
	
}