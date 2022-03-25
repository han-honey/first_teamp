package svc;

import java.sql.Connection;

import dao.BoardEventDAO;
import db.JdbcUtil;
import vo.BoardEventDTO;

public class BoardEventWriteProService {
	public boolean registArticle(BoardEventDTO boardEvent) {
		System.out.println("BoardDriverWriteProService - registArticle()");
		
		boolean isWriteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		BoardEventDAO boardEventDAO = BoardEventDAO.getInstance();
		
		boardEventDAO.setConnection(con);
		
		int insertCount = boardEventDAO.insertBoardEvent(boardEvent);
		
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
