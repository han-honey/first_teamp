package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.QnAReplyDAO;
import db.JdbcUtil;
import vo.QnAReplyDTO;

public class BoardQnAReplyListService {

	public int getQnAReplyListCount(int idx) {
		System.out.println("BoardQnAReplyListService - getQnAReplyListCount()");
		
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		QnAReplyDAO dao = QnAReplyDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectQnAReplyListCount(idx);
		
		JdbcUtil.close(con);
		
		return listCount;
	}
	
	public ArrayList<QnAReplyDTO> getQnAReplyList(int idx) {
		System.out.println("BoardQnAReplyListService - getQnAReplyList()");
		ArrayList<QnAReplyDTO> replyList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		QnAReplyDAO dao = QnAReplyDAO.getInstance();
		
		dao.setConnection(con);
		
		replyList = dao.selectQnAReplyList(idx);
		
		JdbcUtil.close(con);
		
		return replyList;
	}
	
}
