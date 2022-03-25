package svc;

import java.sql.Connection;

import dao.ProductBoardDAO;

import static db.JdbcUtil.*;

public class AdminProductDeleteProService {

	public boolean removeArticle(int product_idx) {
		System.out.println("AdminProductDeleteProService");
		
		boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		int deleteCount =dao.deletArticle(product_idx);
		
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
