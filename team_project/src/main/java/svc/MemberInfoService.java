package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.MemberDTO;

public class MemberInfoService {

	public MemberDTO selectMemberInfo(String id) {
		System.out.println("MemberInfoService-selectMemberInfo()"+"아이디 : " + id);
		MemberDTO dto = null;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		dto = memberDAO.selectMember(id);
		
		if(dto == null) {
			rollback(con);
		} else {
			commit(con);
		}
		close(con);
		return dto;
	}
	
	public ArrayList<MemberDTO> selectMemberList() {
		System.out.println("MemberInfoService-selectMemberList()");
		ArrayList<MemberDTO> memberList = null;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		memberList = memberDAO.selectMemberList();
		
		if(memberList == null) {
			rollback(con);
		} else {
			commit(con);
		}
		close(con);
		return memberList;
	}
}
