package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberIdCheckProService {
	
	// 글 삭제를 위한 본인 확인(패스워드 일치) 작업을 요청할 isArticleWriter() 메서드 정의
	public boolean isDuplicate( String id2) {
		System.out.println("MemberIdCheckProService - isDuplicate()");
		boolean isduplicate = false;
		
		// 공통작업-1 - JdbcUtil 클래스로부터 Connection 객체 가져오기
		Connection con = getConnection();
		
		// 공통작업-2 - FileBoardDAO 클래스로부터 FileBoardDAO 객체 가져오기
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		// 공통작업-3 - Connection 객체를 FileBoardDAO 객체에 전달
		memberDAO.setConnection(con);
		
		// FileBoardDAO 클래스의 isArticleWriter() 메서드 호출하여
		// 작성자 본인 여부 판별(pass 와 num 을 사용하여 일치 여부 판별) 요청
		// => 파라미터 : 글번호, 패스워드    리턴타입 : boolean
		isduplicate = memberDAO.checkDupleId(id2);
		
		
		// 공통작업-4 - Connection 객체 반환
		close(con);
		
		return isduplicate;
	}

	
}














