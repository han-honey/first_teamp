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
import dao.MemberDAO;
import java_mail.SMTPAuthentication;
import vo.ActionForward;
import vo.Email_certificationDTO;

public class MemberEmailCheckService {

	public boolean checkMail(String email, int cert_num) { //인증 메일 전송
		System.out.println("MemberEmailCheckService-checkMail()");
		boolean isCertCheck = false;
		
		Connection con = getConnection();
		
		Email_certificationDAO email_certificationDAO = Email_certificationDAO.getInstance();
		
		email_certificationDAO.setConnection(con);
		
		isCertCheck = email_certificationDAO.checkCertNum(email, cert_num); //db에 저장된 인증번호와 일치여부 판별
		System.out.println("service에서 판별 결과 : " + isCertCheck);
		if(isCertCheck) {
			email_certificationDAO.deleteCertNum(email); //인증 성공 시, db에 저장된 인증번호, 이메일 레코드 삭제
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return isCertCheck;
	}
	
}
