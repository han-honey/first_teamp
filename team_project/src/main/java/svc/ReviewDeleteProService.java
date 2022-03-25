package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewDAO;



public class ReviewDeleteProService {
	

	public boolean removeArticle(int review_idx ) {
		System.out.println("ReviewDeleteProService - removeArticle");
		boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
	    ReviewDAO dao= ReviewDAO.getInstance();
		dao.setConnection(con);
		int deleteCount = dao.deleteArticle(review_idx);
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
