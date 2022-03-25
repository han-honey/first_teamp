package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import dao.Email_certificationDAO;
import java_mail.SMTPAuthentication;
import vo.Email_certificationDTO;

public class MemberEmailSendService {

	public boolean sendMail(Email_certificationDTO dto) { //인증 메일 전송
		System.out.println("MemberEmailSendService-sendMail()");
		boolean isSendMail = false;
		
		Connection con = getConnection();
		
		Email_certificationDAO email_certificationDAO = Email_certificationDAO.getInstance();
		
		email_certificationDAO.setConnection(con);
		
		email_certificationDAO.insertEmail(dto); //db에 이메일, 인증번호 저장
		
		String sender = "nccs0909@gmailcom"; // 보내는 사람 주소
		String receiver = dto.getMember_email(); // 받는 사람 주소
		String subject = null;
		String content = null;
		if(dto.getMember_id()==null) { //가입인증 메일인 경우
				subject = "푸릇푸릇 이메일 인증 코드"; // 메일 제목
				content = "푸릇푸릇에 가입하신 것을 환영합니다.<br> 아래의 인증코드를 입력하시면 가입이 정상적으로"
				+ "완료됩니다.<br> 인증번호 : "+dto.getCertification_num(); // 메일 본문
		}else { //임시비밀번호 전송 메일인 경우
			subject = "푸릇푸릇 임시 비밀번호"; // 메일 제목
				content = dto.getMember_id() + "님의 임시 비밀번호 입니다. 로그인 후 비밀번호를 변경해주세요.<br>"
						+ "임시 비밀번호 : "+dto.getTemp_password(); // 메일 본문
		}

		try {
			System.out.println("메일보내기 츄라이츄라이");
			// -------- 메일 전송을 위한 설정 작업 ---------
			// 메일 전송 프로토콜 : SMTP(Simple Mail Transfer Protocol) - TCP 포트 587번 포트 사용
			// 시스템(서버)의 속성 정보(서버 정보)를 java.util.Properties 객체로 리턴받기
			Properties properties = System.getProperties();
			// 메일 전송에 필요한 정보를 서버 속성 정보에 추가 - put() 메서드 사용
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			// 메일 전송에 사용할 메일 서버 지정(구글, 네이버, 아웃룩 등)
			properties.put("mail.smtp.host", "smtp.gmail.com"); // 구글 메일서버
			properties.put("mail.smtp.auth", "true"); // 인증 여부 설정
			properties.put("mail.smtp.port", "587"); // 메일 전송 서비스 포트 설정

			// 메일 서버 인증 정보를 생성하는 사용자 정의 클래스 인스턴스 생성
			Authenticator authenticator = new SMTPAuthentication(); // 업캐스팅 활용

			// 자바메일에서 메일 전송 기본 단위를 javax.mail.Session 객체 단위로 관리
			// => Session 클래스의 getDefaultInstance() 메서드를 호출하여
//			    파라미터로 서버 정보와 인증 정보를 전달
			Session mailSession = Session.getDefaultInstance(properties, authenticator);

			// 서버와 인증 정보를 포함하는 Message 객체 생성
			// => 파라미터로 Session 객체 전달
			// => Message 객체를 사용하여 전송할 메일에 대한 정보 생성 가능
			Message mailMessage = new MimeMessage(mailSession);

			// 전송할 메일에 대한 정보 생성
			// 1. 발신자 정보 설정
			// => 단, 스팸 메일 정책으로 인해 상용 메일 사이트(구글, 네이버 등)는 발신자 주소 변경 불가능
			Address sender_address = new InternetAddress(sender, "푸릇푸릇");
			// 2. 수신자 정보 설정
			Address receiver_address = new InternetAddress(receiver);
			// 3. Message 객체를 통해 메세지 정보 생성
			// 1) 메일 헤더 정보 설정
			mailMessage.setHeader("content-Type", "text/html; charset=UTF-8");
			// 2) 발신자 정보 설정
			mailMessage.setFrom(sender_address);
			// 3) 수신자 정보 설정
			mailMessage.addRecipient(RecipientType.TO, receiver_address);
			// 4) 메일 제목 설정
			mailMessage.setSubject(subject);
			// 5) 메일 본문 설정
			mailMessage.setContent(content, "text/html; charset=UTF-8");
			// 6) 메일 전송 날짜 정보 설정(java.util.Date 객체를 활용하여 현재 시각 정보로 설정)
			mailMessage.setSentDate(new Date());

			// 4. 메일 전송
			// javax.mail.Transport 클래스의 static 메서드 send() 호출
			Transport.send(mailMessage);
			isSendMail=true;
			commit(con);
		} catch(Exception e) {
			rollback(con);
		}
		
		close(con);
		return isSendMail;
	}
	
}
