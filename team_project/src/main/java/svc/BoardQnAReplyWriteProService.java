package svc;

import java.sql.Connection;

import dao.QnAReplyDAO;
import db.JdbcUtil;
import vo.QnAReplyDTO;

public class BoardQnAReplyWriteProService {

	public boolean isMember(QnAReplyDTO dto) {
		System.out.println("BoardQnAReplyWriteProService - isMember()");
		
		boolean isMember = false;
		
		Connection con = JdbcUtil.getConnection();
		
		QnAReplyDAO dao = QnAReplyDAO.getInstance();
		
		dao.setConnection(con);
		
		boolean isThere = dao.isMemberId(dto);
		
		if(isThere) {
			JdbcUtil.commit(con);
			isMember = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isMember;
	}

	public boolean registReply(QnAReplyDTO dto) {
		System.out.println("BoardQnAReplyWriteProService - registReply()");
		
		boolean isSuccessRegist = false;
		
		Connection con = JdbcUtil.getConnection();
		
		QnAReplyDAO dao = QnAReplyDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertQnAReply(dto);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
			isSuccessRegist = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isSuccessRegist;
	}
}
