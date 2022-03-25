package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;


public class BoardNoticeDeleteProService {
	
		public boolean isArticleWriter(String id) {
			System.out.println("BoardNoticeDeleteProService - isArticleWriter()");
			boolean isArticleWriter = false;
			
			Connection con = getConnection();
			
			BoardDAO boardDAO = BoardDAO.getInstance();
			
			boardDAO.setConnection(con);
			
			isArticleWriter = boardDAO.isArticleWriterId(id);
			
			close(con);
			
			return isArticleWriter;
		}

		public boolean removeArticle(int idx) {
			boolean isDeleteSuccess = false;
			
			Connection con = getConnection();
			
			BoardDAO boardDAO = BoardDAO.getInstance();
			
			boardDAO.setConnection(con);
			
			int deleteCount = boardDAO.deleteArticle(idx);
			
			if(deleteCount > 0) {
				commit(con);
				isDeleteSuccess = true;
			} else {
				rollback(con);
			}
			
			close(con);
			
			return isDeleteSuccess;
		}

}
