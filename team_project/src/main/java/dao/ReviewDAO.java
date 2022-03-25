package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import vo.ReviewDTO;

public class ReviewDAO {

	private static ReviewDAO instance = new ReviewDAO();
	
	private ReviewDAO() {}

	public static ReviewDAO getInstance() {
		return instance;
	}
	Connection con;
	
	public void setConnection(Connection con) { 
		this.con = con;
	}
	
	
	public int insert(ReviewDTO dto) {
		
		int insertCount = 0;
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			String sql= "SELECT MAX(review_idx) FROM review";
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			
			int idx = 1;
			
			if(rs.next()) {
				idx = rs.getInt(1)+1;
			}
			
			sql ="INSERT INTO review VALUES(?,?,?,?,?,?,?,now())";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, idx);
			pstmt2.setString(2, dto.getProduct_code());
			pstmt2.setString(3, dto.getMember_id());
			pstmt2.setString(4, dto.getReview_subject());
			pstmt2.setString(5, dto.getReview_content());
			pstmt2.setString(6, dto.getReview_img());
			pstmt2.setInt(7, dto.getStar_scope());
			
			insertCount = pstmt2.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt2);
			close(pstmt1);
		}
		return insertCount;
	}

	public int selectListCount(String product_code) {
		System.out.println("reviewdao - selectListCount()");
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql ="SELECT COUNT(*) FROM review WHERE product_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<ReviewDTO> selectArticeList(int page, int limit ,String product_code) {
		ArrayList<ReviewDTO> list = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (page -1 )*limit;
		
		String sql = "SELECT * FROM review WHERE product_code =? ORDER BY review_idx DESC LIMIT ?,?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product_code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3,limit);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<ReviewDTO>();
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReview_idx(rs.getInt("review_idx"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setReview_subject(rs.getString("review_subject"));
				dto.setReview_content(rs.getString("review_content"));
				dto.setStar_scope(rs.getInt("star_scope"));
				dto.setReview_date(rs.getTimestamp("review_date"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int deleteArticle(int review_idx) {

		int deleteCount = 0; 

		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM review WHERE review_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_idx);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		} 
		return deleteCount;
	
	}

	public ReviewDTO selectArticle(int review_idx) {
		System.out.println("ReviewDAO - slectArticle()");
		
		ReviewDTO dto = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM review WHERE review_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ReviewDTO();
				dto.setReview_idx(rs.getInt("review_idx"));
				dto.setProduct_code(rs.getString("product_code"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setReview_subject(rs.getString("review_subject"));
				dto.setReview_content(rs.getString("review_content"));
				dto.setReview_img(rs.getString("review_img"));
				dto.setStar_scope(rs.getInt("star_scope"));
				dto.setReview_date(rs.getTimestamp("review_date"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return dto;
	}

	public int updateArticle(ReviewDTO dto) {
		System.out.println("updateArticle()");
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE review Set review_subject=?,review_content=?,review_img=?,star_scope=?,member_id=? WHERE review_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getReview_subject());
			pstmt.setString(2, dto.getReview_content());
			pstmt.setString(3, dto.getReview_img());
			pstmt.setInt(4, dto.getStar_scope());
			pstmt.setString(5, dto.getMember_id());
			pstmt.setInt(6, dto.getReview_idx());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCount;
	}

	public double starScope(String product_code) {
		System.out.println("reviewdao - starScope()");
		
		double scope = 0.0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql ="SELECT product_code, AVG(star_scope) FROM review  WHERE product_code=? GROUP BY product_code";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				scope = rs.getDouble("AVG(star_scope)");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return scope;
	}
	
	public int reviewCount(String product_code) {	
		System.out.println("reviewCount()");
		
		int count = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql ="SELECT COUNT(*) FROM review WHERE product_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,product_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return count;
	}
	
	
}
