package svc;

import java.sql.Connection;

import dao.MemberDAO;
import static db.JdbcUtil.*;

public class MemberLoginProService {
	public boolean Login(String id, String pass) {
		boolean isLoginSuccess = false;
		System.out.println("MemberLoginProService-Login");
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int isUser = memberDAO.checkUser(id, pass);
		
		if(isUser > 0) {
			commit(con);
			isLoginSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		return isLoginSuccess;
	}
	
	public void LoginDate(String id) {
		System.out.println("MemberLoginProService-LoginDate");
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int updateCount = memberDAO.UpdateLoginDate(id);
		
		if(updateCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
	}
}
