package dao;

//import db.JdbcUtil;
import static db.JdbcUtil.close; // JdbcUtil 클래스의 close() 메서드 3개만 static import

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberDTO;
import vo.PaymentDTO;
import vo.ProductBoardDTO;

// 실제 비즈니스 로직을 수행할 NoticeBoardDAO 클래스 정의
// => 외부로부터 인스턴스마다 저장할 데이터를 각각 구별해야할 필요가 없으므로
//    싱글톤 패턴을 활용하여 인스턴스를 직접 생성하고 외부로 공유하도록 정의
public class PaymentDAO {
	// -------------------- 싱글톤 패턴 -----------------------
	private static PaymentDAO instance = new PaymentDAO();
	
	private PaymentDAO() {}

	public static PaymentDAO getInstance() { // 리턴 전용이므로 Getter 만 필요
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
	
	public int selectListCount() {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1); 
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public int insertOrderList(PaymentDTO dto) { // 회원가입
		int insertCount = 0;
		System.out.println("dto.getOrder_request_message():" + dto.getOrder_request_message());
		try {
			for(int i=0; i<dto.getOrder_product_amount().length; i++) {
				String sql = "INSERT INTO order_list VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,'상품준비중',now(),1,?,1)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getOrderer_id());
				pstmt.setString(2, dto.getOrderer_name());
				pstmt.setString(3, dto.getReceiver_name());
				pstmt.setString(4, dto.getReceiver_phone());
				pstmt.setInt(5, dto.getReceiver_post());
				pstmt.setString(6, dto.getReceiver_address());
				pstmt.setString(7, dto.getReceiver_extra_Address());
				pstmt.setString(8, dto.getOrder_product_code()[i]);
				pstmt.setInt(9, dto.getOrder_product_amount()[i]);
				pstmt.setString(10, dto.getOrder_coupon());
				pstmt.setInt(11, dto.getBefore_discount_price());
				pstmt.setInt(12, dto.getAfter_discount_price());
	//			pstmt.setInt(12, dto.getOrder_status()); // 기본 = 상품준비중
	//			pstmt.setString(12, dto.getOrder_tracking_num()); // 기본 = 1
				pstmt.setString(13, dto.getOrder_request_message());
	//			pstmt.setInt(14, dto.getMember_total_purchase()); // 기본 = 1, 반품 = 0, 환불 = -1
	
				insertCount = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	public MemberDTO selectOrder(String id) {
		MemberDTO dto = null;

		String sql = "SELECT * FROM member WHERE member_id=? AND ";
		try {
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
//				dto.setMember_joinDate(rs.getTimestamp("member_joinDate")); // 왜 안될까?
				dto.setMember_total_purchase(rs.getInt("member_total_purchase"));
				dto.setMember_marketing_agree(rs.getString("member_marketing_agree"));
				dto.setMember_marketing_agree(rs.getString("member_name"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return dto;
	}
	

	public ArrayList<PaymentDTO> selectOrderList(String id, int page, int limit) {
		ArrayList<PaymentDTO> orderList = null;
		try {
			int startRow = (page - 1) * limit;
			System.out.println("시작 행번호 : " + startRow);
			
			orderList = new ArrayList<PaymentDTO>();
			
			String sql = "SELECT * FROM order_list WHERE orderer_id=? ORDER BY order_date DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			
			ProductBoardDAO dao = ProductBoardDAO.getInstance();
			dao.setConnection(con);
			
			while (rs.next()) {
				PaymentDTO dto = new PaymentDTO();
				ProductBoardDTO productBoardDTO = dao.selectArticle(rs.getString("order_product_code"));
				dto.setOrder_idx(rs.getInt("order_idx"));
				dto.setOrderer_id(rs.getString("orderer_id"));
				dto.setOrderer_name(rs.getString("orderer_name"));
				dto.setReceiver_name(rs.getString("receiver_name"));
				dto.setReceiver_phone(rs.getString("receiver_phone"));
				dto.setReceiver_post(rs.getInt("receiver_post"));
				dto.setReceiver_address(rs.getString("receiver_address"));
				dto.setReceiver_extra_Address(rs.getString("receiver_extra_Address"));
				dto.setOrder_product_code(rs.getString("order_product_code"));
				dto.setOrder_product_amount(rs.getInt("order_product_amount"));
				dto.setOrder_coupon(rs.getString("order_coupon"));
				dto.setBefore_discount_price(rs.getInt("before_discount_price"));
				dto.setAfter_discount_price(rs.getInt("after_discount_price"));
				dto.setOrder_status(rs.getString("order_status"));
				dto.setOrder_date(rs.getTimestamp("order_date"));
				dto.setOrder_tracking_num(rs.getString("order_tracking_num"));
				dto.setOrder_request_message(rs.getString("order_request_message"));
				dto.setOrder_return_refund_check(rs.getInt("order_return_refund_check"));
				dto.setProuct_name(productBoardDTO.getProduct_name());
				dto.setProduct_price(productBoardDTO.getProduct_price());
				orderList.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
			 
		} finally {
			close(rs);
			close(pstmt);
		}

		return orderList;
	}
	
	public ArrayList<PaymentDTO> selectOrderList(int page, int limit) {
		ArrayList<PaymentDTO> orderList = null;
		try {
			int startRow = (page - 1) * limit;
			System.out.println("시작 행번호 : " + startRow);
			
			orderList = new ArrayList<PaymentDTO>();
			
			String sql = "SELECT * FROM order_list ORDER BY order_date DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			ProductBoardDAO dao = ProductBoardDAO.getInstance();
			dao.setConnection(con);
			
			while (rs.next()) {
				PaymentDTO dto = new PaymentDTO();
				ProductBoardDTO productBoardDTO = dao.selectArticle(rs.getString("order_product_code"));
				dto.setOrder_idx(rs.getInt("order_idx"));
				dto.setOrderer_id(rs.getString("orderer_id"));
				dto.setOrderer_name(rs.getString("orderer_name"));
				dto.setReceiver_name(rs.getString("receiver_name"));
				dto.setReceiver_phone(rs.getString("receiver_phone"));
				dto.setReceiver_post(rs.getInt("receiver_post"));
				dto.setReceiver_address(rs.getString("receiver_address"));
				dto.setReceiver_extra_Address(rs.getString("receiver_extra_Address"));
				dto.setOrder_product_code(rs.getString("order_product_code"));
				dto.setOrder_product_amount(rs.getInt("order_product_amount"));
				dto.setOrder_coupon(rs.getString("order_coupon"));
				dto.setBefore_discount_price(rs.getInt("before_discount_price"));
				dto.setAfter_discount_price(rs.getInt("after_discount_price"));
				dto.setOrder_status(rs.getString("order_status"));
				dto.setOrder_date(rs.getTimestamp("order_date"));
				dto.setOrder_tracking_num(rs.getString("order_tracking_num"));
				dto.setOrder_request_message(rs.getString("order_request_message"));
				dto.setOrder_return_refund_check(rs.getInt("order_return_refund_check"));
				dto.setProuct_name(productBoardDTO.getProduct_name());
				dto.setProduct_price(productBoardDTO.getProduct_price());
				orderList.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
			 
		} finally {
			close(rs);
			close(pstmt);
		}

		return orderList;
	}
	
	public PaymentDTO selectOrderResult(String id) { 
		PaymentDTO dto = null;
		try {
			dto = new PaymentDTO();
			String sql = "SELECT * FROM order_list WHERE orderer_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setOrder_idx(rs.getInt("order_idx"));
//				dto.setOrderer_name(rs.getString("orderer_name"));
//				dto.setReceiver_name(rs.getString("receiver_name"));
//				dto.setReceiver_phone(rs.getString("receiver_phone"));
//				dto.setReceiver_post(rs.getInt("receiver_post"));
//				dto.setReceiver_address(rs.getString("receiver_address"));
//				dto.setReceiver_extra_Address(rs.getString("receiver_extra_Address"));
//				dto.setOrder_product_code(rs.getString("order_product_code"));
//				dto.setOrder_product_amount(rs.getInt("order_product_amount"));
//				dto.setOrder_coupon(rs.getString("order_coupon"));
//				dto.setBefore_discount_price(rs.getInt("before_discount_price"));
//				dto.setAfter_discount_price(rs.getInt("after_discount_price"));
//				dto.setOrder_status(rs.getString("order_status"));
				dto.setOrder_date(rs.getTimestamp("order_date"));
//				dto.setOrder_tracking_num(rs.getString("order_tracking_num"));
//				dto.setOrder_request_message(rs.getString("order_request_message"));
//				dto.setOrder_return_refund_check(rs.getInt("order_return_refund_check"));
				System.out.println(rs.getTimestamp("order_date"));
						
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
			 
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(dto.toString());
		return dto;
	}
	
	public PaymentDTO selectOrderResult(String id,int idx) { 
		PaymentDTO dto = null;
		try {
			dto = new PaymentDTO();
			String sql = "SELECT * FROM order_list WHERE orderer_id=? AND order_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, idx);
			rs = pstmt.executeQuery();

			ProductBoardDAO dao = ProductBoardDAO.getInstance();
			dao.setConnection(con);
			
			if (rs.next()) {
				ProductBoardDTO productBoardDTO = dao.selectArticle(rs.getString("order_product_code"));
				dto.setOrder_idx(rs.getInt("order_idx"));
				dto.setOrderer_name(rs.getString("orderer_name"));
				dto.setReceiver_name(rs.getString("receiver_name"));
				dto.setReceiver_phone(rs.getString("receiver_phone"));
				dto.setReceiver_post(rs.getInt("receiver_post"));
				dto.setReceiver_address(rs.getString("receiver_address"));
				dto.setReceiver_extra_Address(rs.getString("receiver_extra_Address"));
				dto.setOrder_product_code(rs.getString("order_product_code"));
				dto.setOrder_product_amount(rs.getInt("order_product_amount"));
				dto.setOrder_coupon(rs.getString("order_coupon"));
				dto.setBefore_discount_price(rs.getInt("before_discount_price"));
				dto.setAfter_discount_price(rs.getInt("after_discount_price"));
				dto.setOrder_status(rs.getString("order_status"));
				dto.setOrder_date(rs.getTimestamp("order_date"));
				dto.setOrder_tracking_num(rs.getString("order_tracking_num"));
				dto.setOrder_request_message(rs.getString("order_request_message"));
				dto.setOrder_return_refund_check(rs.getInt("order_return_refund_check"));
				dto.setProuct_name(productBoardDTO.getProduct_name());
				dto.setProduct_price(productBoardDTO.getProduct_price());
						
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
			 
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(dto.toString());
		return dto;
	}

	
	public ArrayList<ProductBoardDTO> selectChartList() { 
		ArrayList<ProductBoardDTO> orderList = null;
		try {
			String sql = "SELECT order_product_code, COUNT(order_product_amount), SUM(before_discount_price)"
					+ "FROM order_list GROUP BY order_product_code;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			orderList = new ArrayList<ProductBoardDTO>();
			while(rs.next()) {
				ProductBoardDTO dto = new ProductBoardDTO();
				dto.setProduct_code(rs.getString("order_product_code"));
				dto.setProduct_sell_amount(rs.getInt("COUNT(order_product_amount)"));
				dto.setProduct_price(rs.getInt("SUM(before_discount_price)"));
				orderList.add(dto);		
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
			 
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(orderList);
		return orderList;
	}
}















