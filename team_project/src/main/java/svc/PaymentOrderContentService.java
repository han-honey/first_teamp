package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import dao.PaymentDAO;
import vo.BoardDTO;
import vo.PaymentDTO;

public class PaymentOrderContentService {
	public PaymentDTO getOrder(int idx, String id) {
		System.out.println("PaymentOrderContentService - getOrder");
		
		PaymentDTO paymentDTO = null;
		
		Connection con = getConnection();
		
		PaymentDAO paymentDAO = PaymentDAO.getInstance();
		
		paymentDAO.setConnection(con);
		
		paymentDTO = paymentDAO.selectOrderResult(id,idx);
		
		close(con);
		
		return paymentDTO;
	}
}
