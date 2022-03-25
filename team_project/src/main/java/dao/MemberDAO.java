package dao;

//import db.JdbcUtil;
import static db.JdbcUtil.close; // JdbcUtil 클래스의 close() 메서드 3개만 static import

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Earning_pointDTO;
import vo.MemberDTO;
import vo.PaymentDTO;
import vo.ReviewDTO;

// 실제 비즈니스 로직을 수행할 NoticeBoardDAO 클래스 정의
// => 외부로부터 인스턴스마다 저장할 데이터를 각각 구별해야할 필요가 없으므로
//    싱글톤 패턴을 활용하여 인스턴스를 직접 생성하고 외부로 공유하도록 정의
public class MemberDAO {
	// -------------------- 싱글톤 패턴 -----------------------
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {}

	public static MemberDAO getInstance() { // 리턴 전용이므로 Getter 만 필요
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
	// --------------------------------------------------------
	
	public int insertMember(MemberDTO dto) { // 회원가입
		int insertCount = 0;

		try {
			
			String sql = "INSERT INTO member VALUES (?,?,?,?,?,?,now(),0,?,null)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMember_id());
			pstmt.setString(2, dto.getMember_name());
			pstmt.setString(3, dto.getMember_password());
			pstmt.setString(4, dto.getMember_birth());
			pstmt.setString(5, dto.getMember_phone());
			pstmt.setString(6, dto.getMember_email());
			pstmt.setString(7, dto.getMember_marketing_agree());

			insertCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	public int checkUser(String id, String password) { // 패스워드 일치확인
		int LoginSuccess = 0;
		System.out.println("MemberDAO - checkUser");	
		try {
			String sql = "SELECT * FROM member WHERE member_id=? AND member_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				LoginSuccess = 1;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return LoginSuccess;

	}

	public MemberDTO selectMember(String id) { // 회원 1명 정보 가져오기
		MemberDTO dto = null;

		try {
			String sql = "SELECT * FROM member WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new MemberDTO();
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_password(rs.getString("member_password"));
				dto.setMember_birth(rs.getString("member_birth"));
				dto.setMember_phone(rs.getString("member_phone"));
				dto.setMember_email(rs.getString("member_email"));
				dto.setMember_joinDate(rs.getTimestamp("member_joinDate"));
				dto.setMember_total_purchase(rs.getInt("member_total_purchase"));
				dto.setMember_marketing_agree(rs.getString("member_marketing_agree"));
				dto.setRecent_login_date(rs.getTimestamp("recent_login_date"));
			}
			dto.setMembership_grade(selectGrade(id));
			System.out.println("등급 조회결과 : " + selectGrade(id));
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return dto;
	}
	
	
	public MemberDTO searchId(String name, String email) { //아이디 찾기
		MemberDTO memberDTO = null;
		System.out.println("MemberDTO-searchId()"+"넘어온 값 : " + name +" " + email);
		try {
			String sql = "SELECT member_id,  member_joinDate FROM member WHERE member_name=? AND member_email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setMember_id(rs.getString("member_id"));
				memberDTO.setMember_joinDate(rs.getTimestamp("member_joinDate"));
			}
			System.out.println("찾은 값 : " + rs.getString("member_id") + rs.getTimestamp("member_joinDate"));
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberDTO;
	
	}
	
	public boolean searchPw(String name, String email, String id) { //비밀번호 찾기 중 기본정보 확인
		boolean isPwExist = false;
		System.out.println("MemberDTO-searchId()"+"넘어온 값 : " + name +" " + email + " " + id);
		try {
			String sql = "SELECT * FROM member WHERE member_name=? AND member_email=? AND member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isPwExist = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return isPwExist;
	}
	
	public int updatePassword(MemberDTO dto) { // 비밀번호 변경
		System.out.println("MemberDAO-updateMemberPassword()");
		int updateCount = 0;
		try {
				String sql = "UPDATE member SET member_password=? WHERE member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getMember_password());
				pstmt.setString(2, dto.getMember_id());

				updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public String selectGrade(String id) { // 회원 등급 조회
		String memberGrade = null;

		try {
			String sql = "SELECT member_id, membership_grade FROM member JOIN membership_standard "
					+ "ON member_total_purchase BETWEEN min_purchase AND max_purchase WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memberGrade = rs.getString("membership_grade");
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberGrade;
	}

	public ArrayList<MemberDTO> selectMemberList() { // 회원 리스트 가져오기
		ArrayList<MemberDTO> memberList = null;
		System.out.println("MemberDAO-selectMemberList()");
		try {
			String sql = "SELECT * FROM view_member m JOIN earning_point e ON m.member_id = e.member_id";
			// view_member 뷰 조회
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			memberList = new ArrayList<MemberDTO>();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_password(rs.getString("member_password"));
				dto.setMember_birth(rs.getString("member_birth"));
				dto.setMember_phone(rs.getString("member_phone"));
				dto.setMember_email(rs.getString("member_email"));
				dto.setMember_joinDate(rs.getTimestamp("member_joinDate"));
				dto.setMember_total_purchase(rs.getInt("member_total_purchase"));
				dto.setMember_marketing_agree(rs.getString("member_marketing_agree"));
				dto.setRecent_login_date(rs.getTimestamp("recent_login_date"));
				dto.setMembership_grade(rs.getString("mgd"));
				dto.setEarning_point(rs.getString("total_earning_point"));;
				memberList.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			
		}
		return memberList;
	}

	public int updateMemberInfo(MemberDTO dto) { // 회원정보 수정
		System.out.println("MemberDAO-updateMemberInfo()");
		int updateCount = 0;
		try {
				String sql = "UPDATE member SET member_email=?, member_phone=?, member_marketing_agree=? WHERE member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getMember_email());
				pstmt.setString(2, dto.getMember_phone());
				pstmt.setString(3, dto.getMember_marketing_agree());
				pstmt.setString(4, dto.getMember_id());

				updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return updateCount;
	}
	
	public boolean checkDupleId(String Id2) { // 회원 아이디 중복확인
		System.out.println("MemberDAO-checkDupleId()");
		boolean isDulicate = false;
		pstmt = null;
		rs = null;
		try {
			String sql = "SELECT * FROM member WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Id2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isDulicate = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return isDulicate;
	}
	

	
	public int pointChange(Earning_pointDTO pointDTO) { // 회원가입 시, 적립금 레코드 생성
		int pointChange = 0;

		try {
			
			String sql = "INSERT INTO earning_point VALUES (?, 0+?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pointDTO.getMember_id());
			pstmt.setInt(2, pointDTO.getEarning_change());
			pstmt.setString(3, pointDTO.getEarning_history());
			pstmt.setInt(4, pointDTO.getEarning_change());

			pointChange = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return pointChange;
	}
	
	public int UpdateLoginDate(String id) { // 회원정보 수정
		int updateCount = 0;
		System.out.println("MemberDAO-UpdateLoginDate()");

		try {
				String sql = "UPDATE member SET recent_login_date=now() WHERE member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return updateCount;

	}

	public int memberDelete(String id) { //회원 탈퇴
		System.out.println("MemberDAO-memberDelete()");
		int DeleteCount = 0;

		try {
			String sql = "DELETE FROM member WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			DeleteCount = pstmt.executeUpdate();
			close(pstmt);
			if(DeleteCount>0) {
				sql = "DELETE FROM earning_point WHERE member_id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, id);
						int pointDeleteCount = pstmt.executeUpdate();
						System.out.println("적립금 삭제 레코드 수 : " + pointDeleteCount);
			}
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return DeleteCount;
	}
	
	public int setEarningPoint(ReviewDTO reviewDTO) { // 회원가입 시, 적립금 레코드 생성
		int pointChange = 0;

		try {
			
			String sql = "INSERT INTO earning_point VALUES (?, 0+?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reviewDTO.getMember_id());
			pstmt.setInt(2, 500);
			pstmt.setString(3, "리뷰작성");
			pstmt.setInt(4, 500);

			pointChange = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return pointChange;
	}
	
	public int updatePurchase(PaymentDTO paymentDTO) { // 비밀번호 변경
		System.out.println("MemberDAO-updateMemberPassword()");
		int updateCount = 0;
		try {
				String sql = "UPDATE member SET member_total_purchase = member_total_purchase+? WHERE member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, paymentDTO.getAfter_discount_price());
				pstmt.setString(2, paymentDTO.getOrderer_id());

				updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
}















