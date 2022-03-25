package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardEventDAO;
import vo.BoardEventDTO;

public class BoardEventModifyService {
	public BoardEventDTO getArticle(int idx) {
		System.out.println("BoardEventModifyService - getArticle");
	
	BoardEventDTO boardDTO = null;
	
	Connection con = getConnection();
	
	BoardEventDAO boardDAO = BoardEventDAO.getInstance();
	
	boardDAO.setConnection(con);
	
	boardDTO = boardDAO.selectArticle(idx);
	
	if(boardDTO == null) {
		rollback(con);
	} else {
		commit(con);
	}

	close(con);
	
	return boardDTO;
	}
}
