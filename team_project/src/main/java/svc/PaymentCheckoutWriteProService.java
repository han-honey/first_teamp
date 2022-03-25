package svc;
import java.sql.Connection;

import dao.MemberDAO;
import dao.PaymentDAO;
import dao.ProductBoardDAO;

import static db.JdbcUtil.*;

import vo.PaymentDTO;

public class PaymentCheckoutWriteProService {

	public boolean checkStockAmount(PaymentDTO paymentDTO) {
		System.out.println("PaymentCheckoutWriteProService - checkStockAmount()");
		boolean isCheck = false;
		
		Connection con = getConnection();
		
		ProductBoardDAO productBoardDAO = ProductBoardDAO.getInstance();
		
		productBoardDAO.setConnection(con);
		
		isCheck = productBoardDAO.checkStock(paymentDTO);
		
		if(isCheck) {
			rollback(con);
		} else {
			commit(con);
		}
		close(con);
		
		return isCheck;
	}
	
	public int insertOrderList(PaymentDTO paymentDTO) {
		System.out.println("PaymentCheckoutWriteProService - insertOrderList()");
		int isOrderSuccess = 0;
		
		Connection con = getConnection();
		
		PaymentDAO paymentDAO = PaymentDAO.getInstance();
		
		paymentDAO.setConnection(con);
		
		isOrderSuccess = paymentDAO.insertOrderList(paymentDTO);
		
			if(isOrderSuccess < 1) {
				rollback(con);
			} else {
				commit(con);
				ProductBoardDAO productBoardDAO = ProductBoardDAO.getInstance();
				con = getConnection();
				productBoardDAO.setConnection(con);
					
				productBoardDAO.updateSellAmount(paymentDTO);
				productBoardDAO.updateStockAmount(paymentDTO);
				commit(con);
				MemberDAO memberDAO = MemberDAO.getInstance();
				memberDAO.setConnection(con);
				memberDAO.updatePurchase(paymentDTO);
				commit(con);
			}
			close(con);
			
		return isOrderSuccess;
		}
	}
	




