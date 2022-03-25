package dao;

//import db.JdbcUtil;
import static db.JdbcUtil.close; // JdbcUtil 클래스의 close() 메서드 3개만 static import

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.security.Timestamp;

import vo.CartDTO;
import vo.MemberDTO;
import vo.WishlistDTO;

// 실제 비즈니스 로직을 수행할 NoticeBoardDAO 클래스 정의
// => 외부로부터 인스턴스마다 저장할 데이터를 각각 구별해야할 필요가 없으므로
//    싱글톤 패턴을 활용하여 인스턴스를 직접 생성하고 외부로 공유하도록 정의
public class CartDAO {
	// -------------------- 싱글톤 패턴 -----------------------
	private static CartDAO instance = new CartDAO();
	
	private CartDAO() {}

	public static CartDAO getInstance() { // 리턴 전용이므로 Getter 만 필요
		return instance;
	}
	// --------------------------------------------------------
	// Service 클래스로부터 Connection 객체를 전달받아 멤버변수에 저장
	Connection con;
	PreparedStatement pstmt;
	PreparedStatement pstmt2;
	ResultSet rs;

	public void setConnection(Connection con) { // 저장 전용이므로 Setter 만 필요
		this.con = con;
	}
	// --------------------------------------------------------
	
	public int cartInsert(CartDTO cartDTO) { // 장바구니 추가
		int insertCount = 0;

		try {
			String sql = "SELECT * FROM cart WHERE member_id=? AND product_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cartDTO.getMember_id());
			pstmt.setString(2, cartDTO.getProduct_code());
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 같은 상품이 있으면 개수만 add
				
				sql = "UPDATE cart SET product_amount = product_amount+? WHERE member_id=? AND product_code=?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, cartDTO.getProduct_amount());
				pstmt2.setString(2, cartDTO.getMember_id());
				pstmt2.setString(3, cartDTO.getProduct_code());
				insertCount = pstmt2.executeUpdate();
				
			} else { // 같은 상품이 없으면 INSERT
			
				sql = "INSERT INTO cart VALUES (null,?,?,?,?)";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, cartDTO.getMember_id());
				pstmt2.setString(2, cartDTO.getProduct_code());
				pstmt2.setInt(3, cartDTO.getProduct_amount());
				pstmt2.setInt(4, cartDTO.getProduct_price());
	
				insertCount = pstmt2.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
			close(pstmt2);
		}
		return insertCount;
	}
public ArrayList<CartDTO> selectCartList(String sessionId) {
		
		ArrayList<CartDTO> cartList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			cartList = new ArrayList<CartDTO>(); // view페이지에 뿌릴 데이터 바구니 생성
			
				String sql = "SELECT * FROM product p LEFT JOIN cart c ON p.product_code=c.product_code WHERE c.member_id=? UNION SELECT * FROM product p RIGHT JOIN cart c ON p.product_code=c.product_code WHERE c.member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sessionId);
				pstmt.setString(2, sessionId);
				rs = pstmt.executeQuery();
			while(rs.next()) {
				CartDTO cartDTO = new CartDTO();
				cartDTO.setMember_id(sessionId);
				cartDTO.setProduct_code(rs.getString("product_code"));
				cartDTO.setProduct_img(rs.getString("product_img"));
				cartDTO.setProduct_name(rs.getString("product_name"));
				cartDTO.setProduct_price(rs.getInt("product_price"));
				cartDTO.setProduct_amount(rs.getInt("product_amount"));
				cartDTO.setProduct_stock(rs.getInt("product_stock"));
				cartList.add(cartDTO);
			}
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectCartlist()");
			e.printStackTrace();
		
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return cartList;
	}

	public ArrayList<CartDTO> selectCartList(String sessionId, String[] product_code) {
		
		ArrayList<CartDTO> cartList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			cartList = new ArrayList<CartDTO>(); // view페이지에 뿌릴 데이터 바구니 생성
			
			for(int i=0; i<product_code.length; i++) {
				String sql = "SELECT * FROM product p LEFT JOIN cart c ON p.product_code=c.product_code WHERE c.member_id=? AND c.product_code=? UNION SELECT * FROM product p RIGHT JOIN cart c ON p.product_code=c.product_code WHERE c.member_id=? AND c.product_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sessionId);
				pstmt.setString(2, product_code[i]);
				pstmt.setString(3, sessionId);
				pstmt.setString(4, product_code[i]);
				rs = pstmt.executeQuery();
				
					while(rs.next()) {
						CartDTO cartDTO = new CartDTO();
						cartDTO.setMember_id(sessionId);
						cartDTO.setProduct_code(rs.getString("product_code"));
						cartDTO.setProduct_img(rs.getString("product_img"));
						cartDTO.setProduct_name(rs.getString("product_name"));
						cartDTO.setProduct_price(rs.getInt("product_price"));
						cartDTO.setProduct_amount(rs.getInt("product_amount"));
						cartList.add(cartDTO);
					}
			}
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectCartlist()");
			e.printStackTrace();
		
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return cartList;
	}
	
	public ArrayList<CartDTO> selectCart(String sessionId) {
		
		ArrayList<CartDTO> cartList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			cartList = new ArrayList<CartDTO>(); // view페이지에 뿌릴 데이터 바구니 생성
			
			String sql = "SELECT * FROM cart WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sessionId);
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				CartDTO cartDTO = new CartDTO();
				cartDTO.setMember_id(sessionId);
				cartDTO.setProduct_amount(rs.getInt("product_amount"));
				cartDTO.setProduct_code(rs.getString("product_code"));
				cartDTO.setProduct_price(rs.getInt("product_price"));
				cartList.add(cartDTO);
				System.out.println(cartDTO);
			}
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectCart()");
			e.printStackTrace();
		
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return cartList;
	}

	public int updateCartAmount(CartDTO cartDTO) { // 장바구니에 개수 업데이트
		int updateCount = 0;

		try {
					
			String sql = "UPDATE cart SET product_amount=product_amount+? WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartDTO.getProduct_amount());
			pstmt.setString(2, cartDTO.getMember_id());

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
	
	public boolean checkDupleCart(CartDTO cartDTO) { // 장바구니 추가 시 중복확인(중복이면 갯수만 +)
		boolean isCheckedCount = false;
		
		try {
			String sql = "SELECT product_code FROM cart WHERE member_id=? AND product_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cartDTO.getMember_id());
			pstmt.setString(2, cartDTO.getProduct_code());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isCheckedCount = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isCheckedCount;
	}
	
	public int cartDelete(String id, String[] product_code) { // 장바구니 삭제
		int deleteCount = 0;

		try {
			
			for(int i=0; i<product_code.length; i++) {
				String sql = "DELETE FROM cart WHERE member_id=? AND product_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, product_code[i]);
				deleteCount = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(deleteCount);
		return deleteCount;
		
	}
	
	public int cartAllDelete(String id) { // 장바구니 삭제
		int deleteCount = 0;

		try {
			
			String sql = "DELETE FROM cart WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	public int selectTotalPrice(String id) { // 장바구니 삭제
		int totalPrice = 0;

		try {
			
			String sql = "SELECT SUM(product_price*product_amount) from cart WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalPrice = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(totalPrice);
		return totalPrice;
	}
	
	public int cartUpdate(String id, String[] product_code, int[] product_amount) { // 장바구니 삭제
		int updateCount = 0;

		try {
			
			for(int i=0; i<product_code.length; i++) {
				String sql = "UPDATE cart SET product_amount=? WHERE member_id=? AND product_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, product_amount[i]);
				pstmt.setString(2, id);
				pstmt.setString(3, product_code[i]);
				updateCount = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("SQL 문법 오류!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(updateCount);
		return updateCount;
		
	}

}















