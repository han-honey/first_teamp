package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.JdbcUtil.close;

import vo.PaymentDTO;
import vo.ProductBoardDTO;

public class ProductBoardDAO {

	private static ProductBoardDAO instance = new ProductBoardDAO();

	private ProductBoardDAO() {
	}

	public static ProductBoardDAO getInstance() {
		return instance;
	}

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int insert(ProductBoardDTO dto) {

		int insertCount = 0;

		PreparedStatement pstmt = null;

		try {

			String sql = "INSERT INTO product VALUES(null,?,?,?,?,?,?,?,now(),?,null,null)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getProduct_category1());
			pstmt.setString(2, dto.getProduct_category2());
			pstmt.setString(3, dto.getProduct_code());
			pstmt.setString(4, dto.getProduct_name());
			pstmt.setString(5, dto.getProduct_img());
			pstmt.setString(6, dto.getProduct_info_img());
			pstmt.setInt(7, dto.getProduct_price());
			pstmt.setInt(8, dto.getProduct_stock()); // 수민씨의 stock 추가하는 sql문 넣음

			insertCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return insertCount;
	}

	// 전체 상품 갯수 카운트
	public int selectListCount(String product_category1) {
		System.out.println("ProductBoardDAO - selectListCount");
		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = null;

			if (product_category1.equals("ALL")) {
				sql = "SELECT COUNT(*)FROM product";
				pstmt = con.prepareStatement(sql);

			} else {
				sql = "SELECT COUNT(*)FROM product WHERE product_category1=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, product_category1);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectListCount()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public int selectListCount() { // 수민씨의 파라미터없는 메서드
		System.out.println("ProductBoardDAO - selectListCount");
		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT COUNT(*)FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectListCount()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public ArrayList<ProductBoardDTO> selectList(int page, int limit, String product_category1) {
		System.out.println("ProductBoardDAO - selectList");

		ArrayList<ProductBoardDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			int startRow = (page - 1) * limit;
			String sql = null;

			if (product_category1.equals("ALL")) {
				sql = "SELECT * FROM product ORDER BY product_idx DESC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, limit);

			} else {
				sql = "SELECT * FROM product WHERE product_category1=? ORDER BY product_idx DESC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, product_category1);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, limit);
			}

			rs = pstmt.executeQuery();

			list = new ArrayList<ProductBoardDTO>();

			while (rs.next()) {

				ProductBoardDTO dto = new ProductBoardDTO();

				dto.setProduct_idx(rs.getInt("product_idx"));
				dto.setProduct_category1(rs.getString("product_category1"));
				dto.setProduct_category2(rs.getString("product_category2"));
				dto.setProduct_code(rs.getString("product_code"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_price(rs.getInt("product_price"));
				dto.setProduct_stock(rs.getInt("product_stock"));
				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectList()");
			e.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<ProductBoardDTO> selectList(int page, int limit) { // 수민씨의 파라미터에 카테고리가 없는 메서드
		System.out.println("ProductBoardDAO - selectList");
		ArrayList<ProductBoardDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			int startRow = (page - 1) * limit;

			String sql = "SELECT * FROM product ORDER BY product_idx DESC LIMIT ?,?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);

			rs = pstmt.executeQuery();

			list = new ArrayList<ProductBoardDTO>();

			while (rs.next()) {

				ProductBoardDTO dto = new ProductBoardDTO();

				dto.setProduct_idx(rs.getInt("product_idx"));
				dto.setProduct_category1(rs.getString("product_category1"));
				dto.setProduct_category2(rs.getString("product_category2"));
				dto.setProduct_code(rs.getString("product_code"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_img(rs.getString("product_info_img"));
				dto.setProduct_price(rs.getInt("product_price"));
				dto.setProduct_date(rs.getTimestamp("product_date"));
				dto.setProduct_stock(rs.getInt("product_stock"));

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectList()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public ProductBoardDTO selectArticle(String product_code) {

		System.out.println("ProductBoardDAO - selectArticle() 메서드 호출");

		ProductBoardDTO dto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM product WHERE product_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new ProductBoardDTO();
				dto.setProduct_idx(rs.getInt("product_idx"));
				dto.setProduct_category1(rs.getString("product_category1"));
				dto.setProduct_category2(rs.getString("product_category2"));
				dto.setProduct_code(rs.getString("product_code"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_info_img(rs.getString("product_info_img"));
				dto.setProduct_price(rs.getInt("product_price"));
				dto.setProduct_stock(rs.getInt("product_stock"));
			}
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectArticle()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return dto;
	}

// ----------------------------------------------------------------------------------
	// 검색한 상품리스트 가져오기 - searchProductList()

	public ArrayList<ProductBoardDTO> selectProductList(String searchProductName, int page, int limit) {
		System.out.println("ProductBoardDAO - searchProductList");
		ArrayList<ProductBoardDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			int startRow = (page - 1) * limit;

			String sql = "SELECT * FROM product WHERE product_name LIKE ? ORDER BY product_idx DESC LIMIT ?,?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchProductName + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);

			rs = pstmt.executeQuery();

			list = new ArrayList<ProductBoardDTO>();

			while (rs.next()) {

				ProductBoardDTO dto = new ProductBoardDTO();

				dto.setProduct_idx(rs.getInt("product_idx"));
				dto.setProduct_category1(rs.getString("product_category1"));
				dto.setProduct_category2(rs.getString("product_category2"));
				dto.setProduct_code(rs.getString("product_code"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_price(rs.getInt("product_price"));

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - searchProductList()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

// ----------------------------------------------------------------------------------
	// 상품검색 게시물 갯수 조회 - searchProductListCount()

	public int searchProductListCount(String searchProductName) {
		System.out.println("ProductBoardDAO - searchProductListCount");
		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT COUNT(*)FROM product WHERE product_name LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchProductName + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - searchProductListCount()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	// -----------------------------------------------------------------------
	// 메인페이지 최근업로드순 랭킹
	public ArrayList<ProductBoardDTO> selectNewRankingList() {

		System.out.println("ProductBoardDAO - selectNewRankingList");

		ArrayList<ProductBoardDTO> newRankingList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM product ORDER BY product_date DESC LIMIT 3";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			newRankingList = new ArrayList<ProductBoardDTO>();

			while (rs.next()) {
				ProductBoardDTO dto = new ProductBoardDTO();

				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_price(rs.getInt("product_price"));
				dto.setProduct_code(rs.getString("product_code"));
				newRankingList.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectNewRankingList()");
			e.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);
		}

		return newRankingList;
	}

	// -----------------------------------------------------------------------
	// 메인페이지 최다판매순 랭킹
	public ArrayList<ProductBoardDTO> selectSellRankingList() {

		System.out.println("ProductBoardDAO - selectSellRankingList");

		ArrayList<ProductBoardDTO> sellRankingList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM product ORDER BY product_sell_amount DESC LIMIT 3";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			sellRankingList = new ArrayList<ProductBoardDTO>();

			while (rs.next()) {
				ProductBoardDTO dto = new ProductBoardDTO();

				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_price(rs.getInt("product_price"));
				dto.setProduct_code(rs.getString("product_code"));

				sellRankingList.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectSellRankingList()");
			e.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);
		}

		return sellRankingList;
	}

	// -----------------------------------------------------------------------
	// 메인페이지 리뷰평점순 랭킹
	public ArrayList<ProductBoardDTO> selectStarScoreRankingList() {

		System.out.println("ProductBoardDAO - selectStarScoreRankingList");

		ArrayList<ProductBoardDTO> starScoreRankingList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM review_1 ORDER BY avg_star DESC LIMIT 3";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			starScoreRankingList = new ArrayList<ProductBoardDTO>();

			while (rs.next()) {
				ProductBoardDTO dto = new ProductBoardDTO();

				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_price(rs.getInt("product_price"));
				dto.setProduct_code(rs.getString("product_code"));

				starScoreRankingList.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("sql 구문 오류 발생 - selectStarScoreRankingList()");
			e.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);
		}

		return starScoreRankingList;
	}

	public int deletArticle(int product_idx) {
		System.out.println("deletArticle()");

		int deleteCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM product WHERE product_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product_idx);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return deleteCount;
	}

	public void updateSellAmount(PaymentDTO dto) { // 결제완료 시 해당 상품 sellCount 증가시키기
		PreparedStatement pstmt = null;

		try {

			String sql = "UPDATE product SET product_sell_amount = product_sell_amount + ? WHERE product_code=?";
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < dto.getOrder_product_amount().length; i++) {
				pstmt.setInt(1, dto.getOrder_product_amount()[i]);
				pstmt.setString(2, dto.getOrder_product_code()[i]);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

	}
	
	public void updateStockAmount(PaymentDTO dto) { // 결제완료 시 해당 상품 sellCount 증가시키기
		
		PreparedStatement pstmt = null;

		try {

			String sql = "UPDATE product SET product_stock = product_stock - ? WHERE product_code=?";
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < dto.getOrder_product_amount().length; i++) {
				pstmt.setInt(1, dto.getOrder_product_amount()[i]);
				pstmt.setString(2, dto.getOrder_product_code()[i]);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

	}

	public int updateArticle(ProductBoardDTO dto) {
		System.out.println("updateArticle()");

		int updateCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE product SET product_stock=? WHERE product_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getProduct_stock());
			pstmt.setString(2, dto.getProduct_code());

			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public boolean checkStock(PaymentDTO paymentDTO) {
		boolean isCheck = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			for(int i=0; i<paymentDTO.getOrder_product_code().length; i++) {
				
				String sql = "SELECT * FROM product WHERE product_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, paymentDTO.getOrder_product_code()[i]);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getInt("product_stock") < paymentDTO.getOrder_product_amount()[i]) {
						isCheck = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return isCheck;
	}
	
	

}