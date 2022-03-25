package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardEventDAO;
import vo.BoardEventDTO;

public class BoardEventModifyProService {
		
	public boolean isArticleWriter(String pass) {
		System.out.println("BoardEventModifyProService - isArticleWriter()");
		boolean isArticleWriter = false;
		
		Connection con = getConnection();
		
		BoardEventDAO boardDAO = BoardEventDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		isArticleWriter = boardDAO.isArticleWriter(pass);
		
		close(con);
		
		return isArticleWriter;
	}

	public boolean modifyArticle(BoardEventDTO board) {
		System.out.println("BoardEventModifyProService-modifyArticle");
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		
		BoardEventDAO boardDAO = BoardEventDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		int updateCount = boardDAO.updateArticle(board);
		
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isModifySuccess;
	}
}
