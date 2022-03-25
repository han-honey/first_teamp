package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnADAO;
import vo.QnADTO;


public class BoardQnAAnswerFormService {

	public QnADTO getArticle(int idx) {
		System.out.println("BoardQnAAnswerFormService - getArticle");
	
		QnADTO qnaDTO = null;
		
		Connection con = getConnection();
		
		QnADAO qnaDAO = QnADAO.getInstance();
		
		qnaDAO.setConnection(con);
		
		qnaDTO = qnaDAO.selectArticle(idx);
		
		if(qnaDTO == null) {
			rollback(con);
		} else {
			commit(con);
		}
	
		close(con);
		
		return qnaDTO;
	}
}
