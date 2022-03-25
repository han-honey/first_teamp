package svc;

import java.sql.Connection;

import static db.JdbcUtil.*;
import dao.ProductBoardDAO;
import vo.ProductBoardDTO;

public class BoardProductWriteProService {

	public boolean registArticle(ProductBoardDTO dto) {
		
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insert(dto);
		
		if(insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		
		return isWriteSuccess;
	}
	
}













