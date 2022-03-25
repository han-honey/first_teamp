package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnADAO;
import vo.QnADTO;

public class BoardQnAModifyService {
	public QnADTO getArticle(int idx) {
		System.out.println("BoardQnAModifyService - getArticle");
	
		QnADTO qnaDTO = null;
	
	Connection con = getConnection();
	
	QnADAO DAO = QnADAO.getInstance();
	
	DAO.setConnection(con);
	
	qnaDTO = DAO.selectArticle(idx);
	
	if(qnaDTO == null) {
		rollback(con);
	} else {
		commit(con);
	}

	close(con);
	
	return qnaDTO;
	}
}
