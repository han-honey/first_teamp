package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnADAO;
import vo.QnADTO;



public class BoardQnAModifyProService {
	
	public boolean isArticleWriter(String pass) {
		System.out.println("BoardQnAModifyProService - isArticleWriter()");
		boolean isArticleWriter = false;
		
		Connection con = getConnection();
		
		QnADAO qnaDAO = QnADAO.getInstance();
		
		qnaDAO.setConnection(con);
		
		isArticleWriter = qnaDAO.isArticleWriter(pass);
		
		close(con);
		
		return isArticleWriter;
	}

	public boolean modifyArticle(QnADTO board) {
		System.out.println("BoardQnAModifyProService - modifyArticle()");
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		
		QnADAO qnaDAO = QnADAO.getInstance();
		
		qnaDAO.setConnection(con);
		
		int updateCount = qnaDAO.updateArticle(board);
		
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
