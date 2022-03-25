package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.QnADAO;
import vo.QnADTO;


public class BoardQnAListService {
	
	public int getQnAListCount() {
		System.out.println("QuestionAnswerListService - getQnAListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		
		QnADAO qnaDAO = QnADAO.getInstance();		
		
		qnaDAO.setConnection(con);
		
		listCount = qnaDAO.selectQnAListCount();
		
		close(con);
		
		return listCount;
	}
	
	public ArrayList<QnADTO> getQnAArticleList(int pageNum, int listLimit) {
		System.out.println("QuestionAnswerListService - getQnAArticleList()");
		ArrayList<QnADTO> articleList = null;
		
		Connection con = getConnection();
		
		QnADAO qnaDAO = QnADAO.getInstance();
		
		qnaDAO.setConnection(con);
		
		articleList = qnaDAO.selectQnAArticleList(pageNum, listLimit);
		
		close(con);
		
		return articleList;
	}
}
