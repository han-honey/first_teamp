package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.ProductBoardDAO;
import vo.ProductBoardDTO;

public class BoardProductContentService {

	public ProductBoardDTO getArticle(String product_code) {
		
		System.out.println("BoardProductContentService - getArticle() 메서드 호출");
		
		
		ProductBoardDTO dto = null;
		
		Connection con = getConnection();
		
		ProductBoardDAO dao = ProductBoardDAO.getInstance();
		
		dao.setConnection(con);
		
		dto = dao.selectArticle(product_code);
		
		if(dto == null) {
			rollback(con);
		} else {
			commit(con);
		}

		close(con);
		
		System.out.println("BoardProductContentService - DAO 파일에서 담아온 dto : " + dto);
		
		return dto;
	}

}
