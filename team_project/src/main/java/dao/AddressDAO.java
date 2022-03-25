package dao;

//import db.JdbcUtil;
import static db.JdbcUtil.*; // JdbcUtil 클래스의 close() 메서드 3개만 static import

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.AddressDTO;

// 실제 비즈니스 로직을 수행할 NoticeBoardDAO 클래스 정의
// => 외부로부터 인스턴스마다 저장할 데이터를 각각 구별해야할 필요가 없으므로
//    싱글톤 패턴을 활용하여 인스턴스를 직접 생성하고 외부로 공유하도록 정의
public class AddressDAO {
	// -------------------- 싱글톤 패턴 -----------------------
	private static AddressDAO instance = new AddressDAO();
	
	private AddressDAO() {}

	public static AddressDAO getInstance() { // 리턴 전용이므로 Getter 만 필요
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
	
	public int insertAddress(AddressDTO addressDTO) { // 주소입력
		int insertCount = 0;
		System.out.println("AddressDAO - insertAddress()");
		try {
			String sql = "INSERT INTO address_list VALUES (null,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, addressDTO.getMember_id());
			pstmt.setString(2, addressDTO.getMember_address_sequence());
			pstmt.setInt(3, addressDTO.getAddress_post());
			pstmt.setString(4, addressDTO.getAddress());
			pstmt.setString(5, addressDTO.getExtra_address());
			pstmt.setString(6, addressDTO.getRequest_message());
			pstmt.setInt(7, addressDTO.getAddress_default());

			insertCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	public boolean selectDefaultAddress(String id) { // 기본 배송지 유무를 확인
		System.out.println("AddressDAO-selectDefaultAddress()");
		boolean existDefault = false;
		String sql = "SELECT * FROM address_list WHERE member_id=? AND address_default=1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("기본 배송지 유무를 확인");
			if(rs.next()) {
				existDefault = true;
			}
		}catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return existDefault;
	}
	
	public int clearDefault(String id) { // 아이디의 기본배송지 값을 0으로 수정
		System.out.println("AddressDAO-clearDefault()");
		int clearCount = 0;
		
		try {
			String sql = "UPDATE address_list SET address_default=0 WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			System.out.println("기존의 기본배송지를 일반배송지로 수정");
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return clearCount;
	}

	public ArrayList<AddressDTO> selectAddressList(String id) { // 해당 회원 주소 리스트 가져오기
		ArrayList<AddressDTO> memberAddressList = null;
		System.out.println("AddressDAO-selectAddressList() 아이디 :" + id);
		try {
			String sql = "SELECT * FROM address_list WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			memberAddressList = new ArrayList<AddressDTO>();
			while (rs.next()) {
				AddressDTO addressDTO = new AddressDTO();
				addressDTO.setAddress_idx(rs.getInt("address_idx"));
				addressDTO.setMember_id(rs.getString("member_id"));
				addressDTO.setMember_address_sequence(rs.getString("member_address_sequence"));
				addressDTO.setAddress_post(rs.getInt("address_post"));
				addressDTO.setAddress(rs.getString("address"));
				addressDTO.setExtra_address(rs.getString("extra_address"));
				addressDTO.setRequest_message(rs.getString("request_message"));
				addressDTO.setAddress_default(rs.getInt("Address_default"));
				memberAddressList.add(addressDTO);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberAddressList;
	}

	public AddressDTO selectAddress(int address_idx) { // 수정할 주소 하나 가져오기
		AddressDTO addressDTO = null;
		System.out.println("AddressDAO-selectAddress()");
		try {
			String sql = "SELECT * FROM address_list WHERE address_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, address_idx);
			rs = pstmt.executeQuery();
			addressDTO = new AddressDTO();
			while (rs.next()) {
				addressDTO.setAddress_idx(rs.getInt("address_idx"));
				addressDTO.setMember_id(rs.getString("member_id"));
				addressDTO.setMember_address_sequence(rs.getString("member_address_sequence"));
				addressDTO.setAddress_post(rs.getInt("address_post"));
				addressDTO.setAddress(rs.getString("address"));
				addressDTO.setExtra_address(rs.getString("extra_address"));
				addressDTO.setRequest_message(rs.getString("request_message"));
				addressDTO.setAddress_default(rs.getInt("Address_default"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return addressDTO;
	}
	
	public int updateAddressInfo(AddressDTO addressDTO) { // 주소 수정
		int updateCount = 0;

		try {
					
			String sql = "UPDATE address_list SET address_post=?, address=?, extra_address=?, request_message=?, address_default=? WHERE address_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, addressDTO.getAddress_post());
			pstmt.setString(2, addressDTO.getAddress());
			pstmt.setString(3, addressDTO.getExtra_address());
			pstmt.setString(4, addressDTO.getRequest_message());
			pstmt.setInt(5, addressDTO.getAddress_default());
			pstmt.setInt(6, addressDTO.getAddress_idx());

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
	
	public int deleteAddress(int address_idx) { // 주소 삭제
		int deleteCount = 0;
		System.out.println("AddressDAO-deleteAddress()");
		try {
			String sql = "DELETE FROM address_list WHERE address_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, address_idx);
				deleteCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;

	}
	
	public void deleteAllAddress(String id) { // 주소 전체 삭제
		try {
			System.out.println("AddressDAO-deleteAllAddress()");
				String sql = "DELETE FROM address_list WHERE member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				int deleteCount = pstmt.executeUpdate();
				System.out.println("주소록 삭제 레코드 수 : " + deleteCount);
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

	}

}















