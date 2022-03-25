package svc;

import java.sql.Connection;

import dao.BoardEventDAO;
import dao.QnADAO;
import db.JdbcUtil;
import vo.BoardEventDTO;
import vo.QnADTO;

public class BoardQnAWriteProService {
	public boolean registArticle(QnADTO qnADTO) {
		System.out.println("QnAWriteProService - registArticle()");
		
		boolean isWriteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		QnADAO qnADAO = QnADAO.getInstance();
		
		qnADAO.setConnection(con);
		
		int insertCount = qnADAO.insertQuestion(qnADTO);
		System.out.println("insertQuestion() 실행");
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
