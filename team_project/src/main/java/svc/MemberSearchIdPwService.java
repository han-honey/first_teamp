package svc;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberDTO;

import static db.JdbcUtil.*;

public class MemberSearchIdPwService {
	public MemberDTO searchId(String name, String email) {
		MemberDTO memberDTO = null;
		System.out.println("MemberSearchIdService-searchId()");
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		memberDTO = memberDAO.searchId(name, email);
		
		if(memberDTO != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return memberDTO;
	}
	
	public boolean searchPw(String name, String email, String id) {
		System.out.println("MemberSearchIdService-searchPw()");
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		boolean isPwExist = memberDAO.searchPw(name, email, id);
		
		if(isPwExist) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return isPwExist;
	}
}
