package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtil;
import vo.BoardDTO;
import vo.BoardEventCommentDTO;
import vo.BoardEventDTO;

public class BoardEventDAO {

	private static BoardEventDAO instance = new BoardEventDAO();
	
	private BoardEventDAO() {}

	public static BoardEventDAO getInstance() { 
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) { 
		this.con = con;
	}
	
	public int insertBoardEvent (BoardEventDTO boardEvent) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT MAX(event_idx) FROM event";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int idx = 1;
			
			if(rs.next()) { 
				idx = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO event VALUES(?,?,?,?,?,0,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, boardEvent.getEvent_name());
			pstmt.setString(3, boardEvent.getEvent_subject());
			pstmt.setString(4, boardEvent.getEvent_content());
			pstmt.setString(5, boardEvent.getEvent_img());
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertBoardEvent 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}
	
	public int selectEventListCount() {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM event";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1); 
			}
		} catch (SQLException e) {
			System.out.println("selectEventListCount() 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<BoardEventDTO> selectEventArticleList(int pageNum, int listLimit) {
		ArrayList<BoardEventDTO> boardEventList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int startRow = (pageNum - 1) * listLimit;
			System.out.println("시작 행번호 : " + startRow);
			
			String sql = "SELECT * FROM event ORDER BY event_idx DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			rs = pstmt.executeQuery();
			
			boardEventList = new ArrayList<BoardEventDTO>();
			
			while(rs.next()) {
				BoardEventDTO boardEvent = new BoardEventDTO();
				boardEvent.setEvent_idx(rs.getInt("event_idx"));
				boardEvent.setEvent_name(rs.getString("event_name"));
				boardEvent.setEvent_subject(rs.getString("event_subject"));
				boardEvent.setEvent_img(rs.getString("event_img"));
				boardEvent.setEvent_date(rs.getDate("event_date"));
				boardEvent.setEvent_readcount(rs.getInt("event_readcount"));
				
				boardEventList.add(boardEvent);
			}
			
		} catch (SQLException e) {
			System.out.println("selectEventArticleList 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardEventList;
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
			System.out.println("Event isArticleWriter 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isArticleWriter;
		
	}
	
	public BoardEventDTO selectArticle(int idx) {

		BoardEventDTO boardDTO = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM event WHERE event_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardDTO = new BoardEventDTO();
				boardDTO.setEvent_idx(idx);
				boardDTO.setEvent_name(rs.getString("event_name"));
				boardDTO.setEvent_subject(rs.getString("event_subject"));
				boardDTO.setEvent_content(rs.getString("event_content"));
				boardDTO.setEvent_img(rs.getString("event_img"));
				boardDTO.setEvent_date(rs.getDate("event_date"));
				boardDTO.setEvent_readcount(rs.getInt("event_readcount"));
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
	
	public int updateArticle(BoardEventDTO board) {
		System.out.println("BoardEventDAO-updateArticle");
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE event SET event_subject=?,event_content=?,event_date=now() WHERE event_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getEvent_subject());
			pstmt.setString(2, board.getEvent_content());
			pstmt.setInt(3, board.getEvent_idx());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Event updateArticle 구문 오류 발생!");
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
			String sql = "SELECT event_idx FROM event WHERE event_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
					
				sql = "DELETE FROM event WHERE event_idx=?";
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
		System.out.println("BoardEventDAO - isArticleWriterId()");
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
	
	public void updateReadcount(int idx) {
		System.out.println("BoardEventDAO-updateReadcount()");
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE event SET event_readcount=event_readcount+1 WHERE event_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		}  catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}
	
	public int insertComment (String id, String text, int idx) { // 댓글 작성
	      int insertCount = 0;
	      
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         
	         String sql = "SELECT MAX(comment_idx) FROM event_comment";
	         pstmt = con.prepareStatement(sql);
	         
	         rs = pstmt.executeQuery();
	         
	         int comment_idx = 1;
	         
	         if(rs.next()) { 
	            comment_idx = rs.getInt(1) + 1;
	         }
	         
	         sql = "INSERT INTO event_comment VALUES(?,?,?,now(),?)";
	         
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, idx); // 해당 글번호
	         pstmt.setString(2, id);
	         pstmt.setString(3, text);
	         pstmt.setInt(4, comment_idx);
	         insertCount = pstmt.executeUpdate();
	         // 삭제시 필요한 key가 insert 시 생성되므로 ajax 사용 시 동적으로 사용할수있게 임시로 insertCount 변수를 활용하여 댓글 idx 전달
	         insertCount = comment_idx;
	      } catch (SQLException e) {
	         System.out.println("insertComment 구문 오류 발생!");
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      return insertCount;
	      
	   }
	
	public ArrayList<BoardEventCommentDTO> getCommentList(int idx) {
	      ArrayList<BoardEventCommentDTO> commentList = null;
	      
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         String sql = "SELECT * FROM event_comment WHERE event_comment_idx=? ORDER BY event_comment_date DESC";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, idx);
	         rs = pstmt.executeQuery();
	         
	         commentList = new ArrayList<BoardEventCommentDTO>();
	         
	         while(rs.next()) {
	            BoardEventCommentDTO comment = new BoardEventCommentDTO();
	            comment.setEvent_comment_id(rs.getString("event_comment_id"));
	            comment.setEvent_comment_content(rs.getString("event_comment_content"));
	            comment.setEvent_comment_date(rs.getDate("event_comment_date"));
	            comment.setComment_idx(rs.getInt("comment_idx"));
	            
	            commentList.add(comment);
	         }
	         
	      } catch (SQLException e) {
	         System.out.println("getCommentList 오류 발생!");
	         e.printStackTrace();
	      } finally {
	         close(rs);
	         close(pstmt);
	      }
	      
	      return commentList;
	   }
	
	public int deleteComment(int idx) {
	      System.out.println("BoardEventDAO-deleteComment()");
	      int deleteCount = 0;
	      PreparedStatement pstmt = null;
	      try {
	            String sql = "DELETE FROM event_comment WHERE comment_idx=?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, idx);
	            deleteCount = pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         System.out.println("deleteArticle() 오류 발생!");
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         System.out.println("dao에서 : "+deleteCount);
	      }
	      return deleteCount;
	   }
	
}
