package dao;
import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Email_certificationDTO;

public class Email_certificationDAO {
private static Email_certificationDAO instance = new Email_certificationDAO();
	
	private Email_certificationDAO() {}

	public static Email_certificationDAO getInstance() { // 리턴 전용이므로 Getter 만 필요
		return instance;
	}
	// --------------------------------------------------------
	// Service 클래스로부터 Connection 객체를 전달받아 멤버변수에 저장
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public void setConnection(Connection con) { // 저장 전용이므로 Setter 만 필요
		this.con = con;
	}
	
//	----------------------------------------------------------------
	public void insertEmail(Email_certificationDTO dto) { 

		try {
			String sql = "INSERT INTO email_certification VALUES (null, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMember_email());
			pstmt.setInt(2, dto.getCertification_num());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}
	
	public boolean checkCertNum(String email, int num) { // 패스워드 일치확인
		boolean isCertCheck = false;
		System.out.println("MemberDAO - checkUser");	
		try {
			String sql = "SELECT * FROM email_certification WHERE member_email=? AND certification_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				isCertCheck = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return isCertCheck;

	}
	
	public int deleteCertNum(String email) { // 회원 탈퇴
		int deleteCount = 0;

		try {
			String sql = "DELETE FROM email_certification WHERE member_email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			deleteCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;

	}

}
