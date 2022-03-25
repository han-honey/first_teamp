package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.ReviewDTO;

public class ReviewModifyProService {
	
	public boolean modifyArticle(ReviewDTO dto) {
		System.out.println("ReviewModifyProService - modifyArticle()");
		boolean isModifySuccess = false;
		Connection con = getConnection();
		ReviewDAO dao = ReviewDAO.getInstance();
		dao.setConnection(con);
	    int updateCount = dao.updateArticle(dto);
	    if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}
	    close(con);
		return isModifySuccess ;
	}
	
}
