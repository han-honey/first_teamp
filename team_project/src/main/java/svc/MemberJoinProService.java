package svc;

import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.Earning_pointDTO;
import vo.MemberDTO;

public class MemberJoinProService {
	public boolean insertMemberInfo(MemberDTO member) {
		boolean isInsertInfo = false;
		System.out.println("MemberJoinProService-insertMemberInfo");
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int insertInfo = memberDAO.insertMember(member);
		
		if(insertInfo > 0) {
			JdbcUtil.commit(con);
			isInsertInfo = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		return isInsertInfo;
	}
	
	public boolean modifyPoint(Earning_pointDTO pointDTO) {
		System.out.println("MemberJoinProService-modifyPoint");
		boolean isPointChange = false;
		Connection con = JdbcUtil.getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int pointChange = memberDAO.pointChange(pointDTO);
		
		if(pointChange > 0) {
			JdbcUtil.commit(con);
			isPointChange = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isPointChange;
		
	}
}
