package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import dao.BoardEventDAO;
import vo.BoardDTO;
import vo.BoardEventDTO;

public class BoardEventContentService {
	public BoardEventDTO getArticle(int idx) {
			System.out.println("BoardEventContentService - getArticle");
		
		BoardEventDTO boardEventDTO = null;
		
		Connection con = getConnection();
		
		BoardEventDAO boardEventDAO = BoardEventDAO.getInstance();
		
		boardEventDAO.setConnection(con);
		
		boardEventDAO.updateReadcount(idx); //조회수 1 증가
		
		boardEventDTO = boardEventDAO.selectArticle(idx);
		
		if(boardEventDTO == null) {
			rollback(con);
		} else {
			commit(con);
		}

		close(con);
		
		return boardEventDTO;
	}
}
