package svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.JdbcUtil.*;

import vo.MemberDTO;

public class MemberInfoUpdateService {
	public boolean updateInfo(MemberDTO memberDTO) {
		System.out.println("MemberInfoUpdateService-updateInfo()");
		boolean isUpdateSuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int updateCount = memberDAO.updateMemberInfo(memberDTO);
		
		if(updateCount > 0) {
			commit(con);
			isUpdateSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isUpdateSuccess;
	}
	
	public boolean updatePassword(MemberDTO memberDTO) {
		System.out.println("MemberInfoUpdateService-updatePassword()");
		boolean isUpdatePw = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int updateCount = memberDAO.updatePassword(memberDTO);
		
		if(updateCount > 0) {
			commit(con);
			isUpdatePw = true;
		} else {
			rollback(con);
		}
		close(con);
		
		return isUpdatePw;
	}
}
