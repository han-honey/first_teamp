package svc;

import java.sql.Connection;

import dao.MemberDAO;
import dao.ReviewDAO;
import vo.ReviewDTO;
import static db.JdbcUtil.*;

public class ReviewWriteProService {

	public boolean registArticle(ReviewDTO dto) {
		
		System.out.println("ReviewWriteProService");
		boolean isSuccess = false;
		
		Connection con = getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insert(dto);
		
		if(insertCount > 0) {
//			commit(con);
//			close(con);
//			MemberDAO memberDAO = MemberDAO.getInstance();
//			memberDAO.setConnection(con);
//			memberDAO.setEarningPoint(dto);
			commit(con);
			isSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		
		return isSuccess;
	}

}
