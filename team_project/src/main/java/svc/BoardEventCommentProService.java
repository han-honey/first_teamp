package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardEventDAO;
import vo.BoardEventCommentDTO;

public class BoardEventCommentProService {

	public int addComment(String id, String text, int idx) {
		System.out.println("BoardEventCommentProService - addComment()");
		int addComment = 0;

		Connection con = getConnection();

		BoardEventDAO boardEventDAO = BoardEventDAO.getInstance();

		boardEventDAO.setConnection(con);

		addComment = boardEventDAO.insertComment(id, text, idx);

		if (addComment > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return addComment;
	}

	public ArrayList<BoardEventCommentDTO> getEventCommentList(int idx) {
		System.out.println("BoardEventCommentProService - addComment()");
		ArrayList<BoardEventCommentDTO> commentList = null;

		Connection con = getConnection();

		BoardEventDAO boardEventDAO = BoardEventDAO.getInstance();

		boardEventDAO.setConnection(con);

		commentList = boardEventDAO.getCommentList(idx);

		if (commentList != null) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return commentList;
	}
	
	public int deleteComment(int idx) {
		System.out.println("BoardEventCommentProService - deleteComment()");
		int addComment = 0;

		Connection con = getConnection();

		BoardEventDAO boardEventDAO = BoardEventDAO.getInstance();

		boardEventDAO.setConnection(con);

		addComment = boardEventDAO.deleteComment(idx);

		if (addComment > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return addComment;
	}


}
