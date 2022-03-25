package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnADAO;


public class BoardQnADeleteProService {

	public boolean isArticleWriter(String id) {
		System.out.println("BoardQnADeleteProService - isArticleWriter()");
		boolean isArticleWriter = false;
		
		Connection con = getConnection();
		
		QnADAO qnaDAO = QnADAO.getInstance();
		
		qnaDAO.setConnection(con);
		
		isArticleWriter = qnaDAO.isArticleWriterId(id);
		
		close(con);
		
		return isArticleWriter;
	}

	public boolean removeArticle(int idx) {
		boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
		
		QnADAO qnaDAO = QnADAO.getInstance();
		
		qnaDAO.setConnection(con);
		
		int deleteCount = qnaDAO.deleteArticle(idx);
		
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
