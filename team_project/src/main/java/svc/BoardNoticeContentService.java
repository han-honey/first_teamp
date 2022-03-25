package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardDTO;

public class BoardNoticeContentService {
	public BoardDTO getArticle(int idx) {
		System.out.println("BoardNoticeContentService - getArticle");
		
		BoardDTO boardDTO = null;
		
		Connection con = getConnection();
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		boardDAO.updateReadcount(idx);
		
		boardDTO = boardDAO.selectArticle(idx);
		
		if(boardDTO != null) {
			commit(con);
		} else {
			rollback(con);
		}
		

		close(con);
		
		return boardDTO;
	}
}
