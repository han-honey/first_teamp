package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.PaymentDAO;
import vo.PaymentDTO;

public class PaymentOrderListService {

	public int getListCount() {
		System.out.println("BoardListService - getListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		
		PaymentDAO paymentDAO = PaymentDAO.getInstance();		
		
		paymentDAO.setConnection(con);
		
		listCount = paymentDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}
	
	public ArrayList<PaymentDTO> selectOrderList(String id,int pageNum,int listLimit) {
		
		System.out.println("PaymentOrderListService - selectOrderList() 메서드 호출");
		
		ArrayList<PaymentDTO> orderList = null;
		
		Connection con = getConnection();
		PaymentDAO paymentDAO = PaymentDAO.getInstance();
		paymentDAO.setConnection(con);
		
		orderList = paymentDAO.selectOrderList(id,pageNum, listLimit);

		close(con);
		
		
		return orderList;
		
	}
	
}
