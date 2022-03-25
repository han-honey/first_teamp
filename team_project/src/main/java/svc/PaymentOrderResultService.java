package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.PaymentDAO;
import vo.PaymentDTO;

public class PaymentOrderResultService {

	public PaymentDTO selectOrderResult(String id) {
		
		System.out.println("PaymentOrderListService - selectOrderList() 메서드 호출");
		
		PaymentDTO orderList = null;
		
		Connection con = getConnection();
		PaymentDAO paymentDAO = PaymentDAO.getInstance();
		paymentDAO.setConnection(con);
		
		orderList = paymentDAO.selectOrderResult(id);

		close(con);
		
		
		return orderList;
		
	}

}
