package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AddressDAO;
import dao.CartDAO;
import dao.MemberDAO;
public class MemberDeleteProService {

	public boolean deleteMember(String id) {
		System.out.println("MemberDeleteProService-deleteMember()");
		boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int deleteCount = memberDAO.memberDelete(id); // member 테이블에서 회원정보 삭제
		
		if(deleteCount>0) {
			isDeleteSuccess=true;
			AddressDAO addressDAO = AddressDAO.getInstance(); // address 테이블에서 회원주소 삭제
			addressDAO.setConnection(con);
			addressDAO.deleteAllAddress(id);
//			------------------------------------------
			CartDAO cartDAO = CartDAO.getInstance(); // cart 테이블에서 회원 장바구니 삭제
			cartDAO.setConnection(con);
			cartDAO.cartAllDelete(id);
//			---------------------------------------- 쿠폰, 주문목록 테이블 삭제도 추가해야함
			commit(con);
		} else {
			rollback(con);
		}
		return isDeleteSuccess;
	}
}
