package java_mail;

import javax.mail.Authenticator;

import javax.mail.PasswordAuthentication;

// 메일 서버 인증을 위해  javax.mail.Authenticator 클래스를 상속받는 서브클래스 정의
public class SMTPAuthentication extends Authenticator {
	// 인증 정보(아이디, 패스워드)를 관리할 PasswordAuthentication 클래스 타입 변수 선언
	PasswordAuthentication passAuth;
	
	public SMTPAuthentication() {
		// 인증에 사용할 아이디, 패스워드를 갖는 객체 생성
		// => 2단계 인증일 경우 별도의 부가 작업 필요
		//    (Gmail 의 경우 2단계 인증 통과 가능한 비밀번호 별도로 발급) 
		passAuth = new PasswordAuthentication("nccs0909@gmail.com", "jckwjmfbifufvdit");
	}

	// 인증 정보(아이디, 패스워드)를 갖는 PasswordAuthentication 객체를 리턴하는 
	// getPasswordAuthentication() 메서드 오버라이딩 필수!
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return passAuth;
	}
	
	
	
}











