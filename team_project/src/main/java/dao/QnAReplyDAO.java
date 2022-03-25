package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.QnAReplyDTO;

public class QnAReplyDAO {

	private static QnAReplyDAO instance = new QnAReplyDAO();
	
	private QnAReplyDAO() {}

	public static QnAReplyDAO getInstance() {
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public boolean isMemberId(QnAReplyDTO dto) {
		System.out.println("QnAReplyDAO - isMemberId()");
		boolean isMember = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT member_id FROM member WHERE member_id=?";
			
			pstmt = con.prepareStatement(sql);
			System.out.println("QnAReplyDAO - isMemberId()" + dto.getReply_member_id());
			
			pstmt.setString(1, dto.getReply_member_id());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isMember = true;
			}
			
		} catch (SQLException e) {
			System.out.println("isMemberId() 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isMember;
	}

	

	public int insertQnAReply(QnAReplyDTO dto) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT Max(QnA_reply.reply_idx) FROM QnA,QnA_reply WHERE QnA.QnA_idx=QnA_reply.reply_ref";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int idx = 1;
			
			if(rs.next()) {
				idx = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO QnA_reply VALUES (?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, dto.getReply_member_id());
			pstmt.setString(3, dto.getReply_content());
			pstmt.setInt(4, dto.getReply_ref());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertQnAReply() 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}

	public int selectQnAReplyListCount(int idx) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM QnA_reply WHERE reply_ref=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1); 
			}
		} catch (SQLException e) {
			System.out.println("selectQnAListCount() 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}

	public ArrayList<QnAReplyDTO> selectQnAReplyList(int idx) {
		System.out.println("QnAReplyDAO - selectQnAReplyList()");
		ArrayList<QnAReplyDTO> replyList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT reply_member_id,reply_content FROM QnA_reply WHERE reply_ref=? ORDER BY reply_idx ASC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			replyList = new ArrayList<QnAReplyDTO>();
			
			while(rs.next()) {
				QnAReplyDTO dto = new QnAReplyDTO();
				dto.setReply_member_id(rs.getString("reply_member_id"));
				dto.setReply_content(rs.getString("reply_content"));
				
				replyList.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("selectQnAReplyList() 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return replyList;
	}
}
