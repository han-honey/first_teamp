package svc;

import java.sql.Connection;

import dao.ProductBoardDAO;

import static db.JdbcUtil.*;

import vo.ProductBoardDTO;

public class AdminProductModifyProService {

	public boolean modifyArticle(ProductBoardDTO dto) {
		System.out.println("AdminProductModifyProService");
		
		boolean isModifySucces=false;
		Connection con = getConnection();
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		dao.setConnection(con);
		int updateCount = dao.updateArticle(dto);
		if(updateCount > 0) {
			commit(con);
			isModifySucces =true;
		}else {
			rollback(con);
		}
		close(con);
		return isModifySucces;
		
	}

}
