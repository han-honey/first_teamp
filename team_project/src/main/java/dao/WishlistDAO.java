package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import vo.WishlistDTO;

public class WishlistDAO {

	private static WishlistDAO instance = new WishlistDAO();
	private WishlistDAO() {}
	public static WishlistDAO getInstance() {
		return instance;
	}

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// -------------------------------------------------------------------
	// wishlist 조회
	
	public ArrayList<WishlistDTO> selectWishlist(String member_id) {
		System.out.println("WishlistDAO - selectWishlist() 메서드 호출");
		
		ArrayList<WishlistDTO> wishlist = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// ★질문!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		try {
			
			String sql = "SELECT p.product_code, p.product_img, p.product_name, p.product_price "
					+ "FROM product p "
					+ "LEFT JOIN wishlist w ON p.product_code=w.product_code "
					+ "WHERE w.member_id=?";
			
			wishlist = new ArrayList<WishlistDTO>();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WishlistDTO wishlistDTO = new WishlistDTO();
				wishlistDTO.setProduct_code(rs.getString("product_code"));
				wishlistDTO.setProduct_img(rs.getString("product_img"));
				wishlistDTO.setProduct_name(rs.getString("product_name"));
				wishlistDTO.setProduct_price(rs.getInt("product_price"));
				
				wishlist.add(wishlistDTO);
			}
			
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectWishlist()");
			e.printStackTrace();
		
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return wishlist;
	}
	
	
	// -------------------------------------------------------------------
	// wishlist 중복 체크
	
	public boolean wishlistDupCheck (WishlistDTO wishlistDTO) {
		
		System.out.println("WishlistDAO - wishlistDupCheck() 메서드 호출");
		
		boolean isWishlistDupCheck = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM wishlist WHERE member_id=? and product_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, wishlistDTO.getMember_id());
			pstmt.setString(2, wishlistDTO.getProduct_code());			
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isWishlistDupCheck = true;
			} 
			
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - wishlistDupCheck()");
			e.printStackTrace();
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isWishlistDupCheck;
	}
	
	
	
	// -------------------------------------------------------------------
	// wishlist에 아이템 추가
	
	public int addWishlist(WishlistDTO wishlistDTO) {
		
		System.out.println("WishlistDAO - addWishList() 메서드 호출");
		
		int addCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO wishlist VALUES (null,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, wishlistDTO.getMember_id());
			pstmt.setString(2, wishlistDTO.getProduct_code());
			
			addCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - addWishlist()");
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		return addCount;
	}
	
	
	// -------------------------------------------------------------------
	// wishlist 낱개 삭제
	
	public int deleteOneWishlist(WishlistDTO wishlistDTO) {
		
		System.out.println("WishlistDAO - deleteOneWishlist() 메서드 호출");
		
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "DELETE FROM wishlist WHERE member_id=? and product_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, wishlistDTO.getMember_id());
				pstmt.setString(2, wishlistDTO.getProduct_code());
				deleteCount = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - deleteOneWishlist()");
			e.printStackTrace();
		
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return deleteCount;
	}
	
	// -------------------------------------------------------------------
	// wishlist 전체 삭제
	
	public int deleteAllWishlist(WishlistDTO wishlistDTO) {
		
		System.out.println("WishlistDAO - deleteAllWishlist() 메서드 호출");
		
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "DELETE FROM wishlist WHERE member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, wishlistDTO.getMember_id());
				deleteCount = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - deleteAllWishlist()");
			e.printStackTrace();
		
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return deleteCount;
	}	
	
	

}
