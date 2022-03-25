package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnADAO;
import vo.QnADTO;

public class BoardQnAAnswerProService {

	public boolean insertAnswer(QnADTO board) {
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		
		QnADAO qnaDAO = QnADAO.getInstance();
		
		qnaDAO.setConnection(con);
		
		int updateCount = qnaDAO.insertReply(board);
		
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
