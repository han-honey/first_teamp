package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardDTO;

public class BoardNoticeModifyService {
	public BoardDTO getArticle(int idx) {
		System.out.println("BoardNoticeModifyService - getArticle");
	
	BoardDTO boardDTO = null;
	
	Connection con = getConnection();
	
	BoardDAO boardDAO = BoardDAO.getInstance();
	
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
