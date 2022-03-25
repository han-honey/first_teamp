package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtil;

import static db.JdbcUtil.close; 

import vo.BoardDTO;

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {}

	public static BoardDAO getInstance() { 
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) { 
		this.con = con;
	}
	
	public int insertBoard(BoardDTO board) {
		int insertCount = 0;
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT MAX(notice_idx) FROM notice";
			pstmt1 = con.prepareStatement(sql);
			
			rs = pstmt1.executeQuery();
			
			int idx = 1;
			
			if(rs.next()) { 
				idx = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO notice VALUES(?,?,?,?,?,0,now())";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, idx);
			pstmt2.setString(2, board.getName());
			pstmt2.setString(3, board.getSubject());
			pstmt2.setString(4, board.getContent());
			pstmt2.setString(5, board.getImg());
			insertCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertBoard() 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt2);
			close(rs);
			close(pstmt1);
		}
		
		return insertCount;
	}
	
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
	
	public ArrayList<BoardDTO> selectArticleList(int page, int limit) {
		ArrayList<BoardDTO> boardList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int startRow = (page - 1) * limit;
			System.out.println("시작 행번호 : " + startRow);
			
			String sql = "SELECT * FROM notice ORDER BY notice_idx DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<BoardDTO>();
			
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setIdx(rs.getInt("notice_idx"));
				board.setName(rs.getString("notice_name"));
				board.setSubject(rs.getString("notice_subject"));
				board.setImg(rs.getString("notice_img"));
				board.setDate(rs.getDate("notice_date"));
				board.setReadcount(rs.getInt("notice_readcount"));
				
				boardList.add(board);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}

	public boolean isArticleWriter(String pass) {
		boolean isArticleWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT admin_password FROM admin WHERE admin_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pass.equals(rs.getString("admin_password"))) {
					isArticleWriter = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("notice isArticleWriter 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isArticleWriter;
		
	}
	
	public BoardDTO selectArticle(int idx) {

		BoardDTO boardDTO = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM notice WHERE notice_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setIdx(rs.getInt("notice_idx"));
				boardDTO.setName(rs.getString("notice_name"));
				boardDTO.setSubject(rs.getString("notice_subject"));
				boardDTO.setContent(rs.getString("notice_content"));
				boardDTO.setImg(rs.getString("notice_img"));
				boardDTO.setDate(rs.getDate("notice_date"));
				boardDTO.setReadcount(rs.getInt("notice_readcount"));
			}
		} catch (SQLException e) {
			System.out.println("selectArticle() 오류 발생!");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardDTO;
	}
	
	public void updateReadcount(int idx) {
		
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "UPDATE notice SET notice_readcount = notice_readcount+1 WHERE notice_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("notice updateReadcount 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	
	public int updateArticle(BoardDTO board) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE notice SET notice_subject=?,notice_content=?,notice_date=now() WHERE notice_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getIdx());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("notice updateArticle 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}

	public int deleteArticle(int idx) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT notice_idx FROM notice WHERE notice_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
					
				sql = "DELETE FROM notice WHERE notice_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				deleteCount = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("deleteArticle() 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return deleteCount;
	}

	public boolean isArticleWriterId(String id) {
		System.out.println("BoardDAO - isArticleWriterId()");
		boolean isAricleWriterId = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT admin_id FROM admin WHERE admin_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(id.equals(rs.getString("admin_id"))) {
					isAricleWriterId = true;
				}
			
			}
		} catch (SQLException e) {
			System.out.println("isArticleWriterId() 오류 발생!");
			e.printStackTrace();
		}
		System.out.println(isAricleWriterId);
		return isAricleWriterId;
	}
	
	
}















