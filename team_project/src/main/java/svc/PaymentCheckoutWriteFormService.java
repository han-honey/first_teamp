package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AddressDAO;
import dao.CartDAO;
import dao.MemberDAO;

import static db.JdbcUtil.*;

import vo.AddressDTO;
import vo.CartDTO;
import vo.MemberDTO;

public class PaymentCheckoutWriteFormService {
	
	public ArrayList<CartDTO> selectCartList(String id, String[] product_code) {
		System.out.println("PaymentCheckoutWriteFormService - selectCartList()");
		ArrayList<CartDTO> cartList = null;
		
		Connection con = getConnection();
		
		CartDAO cartDAO = CartDAO.getInstance();
		
		cartDAO.setConnection(con);
		
		cartList = cartDAO.selectCartList(id, product_code);
		
		if(cartList == null) {
			rollback(con);
		} else {
			commit(con);
		}
		close(con);
		
		return cartList;
	}
	
	public MemberDTO getMemberInfo(String id) {
		System.out.println("PaymentCheckoutWriteFormService - getMemberInfo()");
		MemberDTO memberDTO = null;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		memberDTO = memberDAO.selectMember(id);
		
		if(memberDTO == null) {
			rollback(con);
		} else {
			commit(con);
		}
		close(con);
		
		return memberDTO;
	}
	
	public ArrayList<AddressDTO> getAddressList(String id) {
		System.out.println("PaymentCheckoutWriteFormService - getAddressList()");
		ArrayList<AddressDTO> cartList = null;
		
		Connection con = getConnection();
		
		AddressDAO addressDAO = AddressDAO.getInstance();
		
		addressDAO.setConnection(con);
		
		cartList = addressDAO.selectAddressList(id);
		
		if(cartList == null) {
			rollback(con);
		} else {
			commit(con);
		}
		close(con);
		
		return cartList;
	}
}




