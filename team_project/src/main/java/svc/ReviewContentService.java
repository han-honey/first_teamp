package svc;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.ReviewDTO;
import static db.JdbcUtil.*;

public class ReviewContentService {

	public ReviewDTO getArticle(int review_idx) {
		System.out.println("ReviewContentService - getArticle()");
	
		ReviewDTO dto  = null;
		
		Connection con = getConnection();

		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
		dto = dao.selectArticle(review_idx);
		
		close(con);
		
		return dto ;
	}

}
