package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.PaymentDAO;
import vo.ProductBoardDTO;
import static db.JdbcUtil.*;

public class AdminChartListService {
	

	public ArrayList<ProductBoardDTO> getList() {
		System.out.println("AdminChartListService - getList() 메서드 호출");
		
		ArrayList<ProductBoardDTO> list = null;

		Connection con = getConnection();
		
		PaymentDAO paymentDAO = PaymentDAO.getInstance();
		
		paymentDAO.setConnection(con);
		
		list =paymentDAO.selectChartList();
		
		close(con);
		
		return list;
	
	}

}
