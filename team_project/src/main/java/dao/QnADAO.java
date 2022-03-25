package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtil;
import vo.QnADTO;

public class QnADAO {

	private static QnADAO instance = new QnADAO();
	
	private QnADAO() {}

	public static QnADAO getInstance() { 
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) { 
		this.con = con;
	}
	
	@SuppressWarnings("resource")
	public int insertQuestion(QnADTO qnaDTO) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT MAX(QnA_idx) FROM QnA";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int idx = 1;
			
			if(rs.next()) { 
				idx = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO QnA ("
					+ "QnA_idx,"
					+ "QnA_category,"
					+ "product_code,"
					+ "member_id,"
					+ "QnA_subject,"
					+ "QnA_content,"
					+ "QnA_img,"
					+ "QnA_date,"
					+ "QnA_ref,"
					+ "QnA_lev,"
					+ "QnA_seq) "
					+ "VALUES("
					+ "?,?,?,?,?,?,?,now(),?,?,?"
					+ ")";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, qnaDTO.getQuestion_category());
			pstmt.setString(3, qnaDTO.getProduct_code());
			pstmt.setString(4, qnaDTO.getMember_id());
			pstmt.setString(5, qnaDTO.getQuestion_subject());
			pstmt.setString(6, qnaDTO.getQuestion_content());
			pstmt.setString(7, qnaDTO.getQuestion_img());
			if(qnaDTO.getAnswer_ref() == 0) {
				pstmt.setInt(8, idx);
				pstmt.setInt(9, 0);
			}else {
				pstmt.setInt(8, qnaDTO.getAnswer_ref());
				pstmt.setInt(9, qnaDTO.getAnswer_lev()+1);
			}						
			pstmt.setInt(10, 0);
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertQuestion() 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}
	
	public int selectQnAListCount() {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM QnA";
			pstmt = con.prepareStatement(sql);
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
	
	public ArrayList<QnADTO> selectQnAArticleList(int page, int limit) {
		ArrayList<QnADTO> qnaList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int startRow = (page - 1) * limit;
			System.out.println("시작 행번호 : " + startRow);
			
			String sql = "SELECT * FROM QnA "
					+ "ORDER BY QnA_ref DESC,QnA_seq ASC "
					+ "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			qnaList = new ArrayList<QnADTO>();
			
			while(rs.next()) {
				QnADTO qnaDTO = new QnADTO();
				qnaDTO.setQuestion_idx(rs.getInt("QnA_idx"));
				qnaDTO.setQuestion_category(rs.getString("QnA_category"));
				qnaDTO.setProduct_code(rs.getString("product_code"));
				qnaDTO.setMember_id(rs.getString("member_id"));
				qnaDTO.setQuestion_subject(rs.getString("QnA_subject"));
				qnaDTO.setQuestion_date(rs.getDate("QnA_date"));
				qnaDTO.setAnswer_ref(rs.getInt("QnA_ref"));
				qnaDTO.setAnswer_lev(rs.getInt("QnA_lev"));
				qnaDTO.setAnswer_seq(rs.getInt("QnA_seq"));
				
				qnaList.add(qnaDTO);
			}
			
		} catch (SQLException e) {
			System.out.println("selectQnAArticleList() 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return qnaList;
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
			System.out.println("isArticleWriter() 오류 발생!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isArticleWriter;
		
	}
	
	public int updateArticle(QnADTO board) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE QnA SET QnA_subject=?,QnA_content=?,QnA_date=now() WHERE QnA_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getQuestion_subject());
			pstmt.setString(2, board.getQuestion_content());
			pstmt.setInt(3, board.getQuestion_idx());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("QnA updateArticle 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}

	public QnADTO selectArticle(int idx) {
		System.out.println("QnADAO - selectArticle");
		
		QnADTO qnaDTO = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM QnA WHERE QnA_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qnaDTO = new QnADTO();
				qnaDTO.setMember_id(rs.getString("member_id"));
				qnaDTO.setQuestion_category(rs.getString("QnA_category"));
				qnaDTO.setProduct_code(rs.getString("product_code"));
				qnaDTO.setQuestion_idx(rs.getInt("QnA_idx"));
				qnaDTO.setAnswer_ref(rs.getInt("QnA_ref"));
				qnaDTO.setAnswer_lev(rs.getInt("QnA_lev"));
				qnaDTO.setQuestion_subject(rs.getString("QnA_subject"));
				qnaDTO.setQuestion_content(rs.getString("QnA_content"));
				qnaDTO.setQuestion_img(rs.getString("QnA_img"));
				qnaDTO.setQuestion_date(rs.getDate("QnA_date"));
			}
		} catch (SQLException e) {
			System.out.println("selectArticle() 오류 발생!");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return qnaDTO;
	}
	
	public boolean isArticleWriterId(String id) {
		System.out.println("QnADAO - isArticleWriterId()");
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
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(isAricleWriterId);
		return isAricleWriterId;
	}
	
	public int deleteArticle(int idx) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT QnA_idx FROM QnA WHERE QnA_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
					
				sql = "DELETE FROM QnA WHERE QnA_idx=?";
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
	
	public int insertReply(QnADTO board) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT MAX(QnA_idx) FROM QnA";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int num = 1;
			
			if(rs.next()) { 
				num = rs.getInt(1) + 1;
			}
			
			int QnA_ref = board.getQuestion_idx();
			int QnA_lev = board.getAnswer_lev();
			int QnA_seq = board.getAnswer_seq();
			
			sql = "UPDATE QnA SET QnA_seq=QnA_seq+1 "
					+ "WHERE QnA_ref=? AND QnA_seq>?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, QnA_ref);
			pstmt.setInt(2, QnA_seq);
			pstmt.executeUpdate();
			
			QnA_lev++;
			QnA_seq++;
			
			sql = "INSERT INTO QnA ("
					+ "QnA_idx"
					+ ",QnA_category"
						+ ",member_id"
							+ ",QnA_subject"
						+ ",QnA_content"
					+ ",QnA_img"
						+ ",QnA_date"
							+ ",QnA_ref"
						+ ",QnA_lev"
					+ ",QnA_seq"
						+ ") VALUES ("
							+ "?,?,?,?,?,?,now(),?,?,?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, num);
			pstmt2.setInt(2, 4);
			pstmt2.setString(3, board.getMember_id());
			pstmt2.setString(4, board.getQuestion_subject());
			pstmt2.setString(5, board.getQuestion_content());
			pstmt2.setString(6, board.getQuestion_img());
			pstmt2.setInt(7, QnA_ref); 
			pstmt2.setInt(8, QnA_lev); 
			pstmt2.setInt(9, QnA_seq); 
			
			insertCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertReply() 오류 발생!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(pstmt2);
		}
		
		return insertCount;
	}
	
}
	

