package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CartDAO;
import vo.CartDTO;

//import db.JdbcUtil;
// static import 기능을 사용하면 특정 클래스 내의 static 메서드들을
// 클래스명 없이 메서드명만으로 호출 가능해짐
// 기본 문법 : import static 패키지명.클래스명.메서드명; 또는 import static 패키지명.클래스명.*;
// => 주의! 메서드명 지정 시 소괄호() 는 명시하지 않음
//import static db.JdbcUtil.getConnection; // getConnection() 메서드만 import
import static db.JdbcUtil.*; // JdbcUtil 클래스의 모든 static 메서드를 import

public class PaymentCartUpdateService {
	
	public int cartUpdate(String sessionId, String[] product_code, int[] product_amount) {
		System.out.println("PaymentCartUpdateService - cartUpdate() 메서드 호출");
		int updateCount = 0;
		
		// 공통작업-1 - JdbcUtil 클래스로부터 Connection 객체 가져오기
		Connection con = getConnection();
		
		// 공통작업-2 - FileBoardDAO 클래스로부터 FileBoardDAO 객체 가져오기
		CartDAO cartDAO = CartDAO.getInstance();		
		
		// 공통작업-3 - Connection 객체를 FileBoardDAO 객체에 전달
		cartDAO.setConnection(con);
		
		// 게시물 목록 조회 작업 요청을 위해 Service 클래스의 selectArticleList() 메서드 호출
		// => 파라미터 : 페이지번호(pageNum), 페이지당 게시물 수(listLimit)
		// => 리턴타입 : ArrayList<FileBoardDTO>(articleList)
		updateCount = cartDAO.cartUpdate(sessionId, product_code, product_amount);
		
		if(updateCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		// 공통작업-4 - Connection 객체 반환
		close(con);
		
		return updateCount;
	}
}












