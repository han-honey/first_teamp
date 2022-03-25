package action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java_mail.SMTPAuthentication;
import svc.MemberEmailSendService;
import vo.ActionForward;
import vo.Email_certificationDTO;
import vo.MemberDTO;

public class MemberEmailSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("MemberEmailSendAction");
			
		
				System.out.println("action으로 넘어온 이메일 : " + request.getParameter("email"));
			
				Random random = new Random();
				int certification_num = random.nextInt(99999);
				
				System.out.println("인증번호 : " + certification_num);
				
				Email_certificationDTO certificationDTO = new Email_certificationDTO();
				certificationDTO.setMember_email(request.getParameter("email"));
				certificationDTO.setCertification_num(certification_num);
//				--------------------------------------------------------------
				
				MemberEmailSendService memberEmailCheckService = new MemberEmailSendService();
				boolean isSendMail = memberEmailCheckService.sendMail(certificationDTO);
				
				if(isSendMail) {
					HttpSession session = request.getSession(true);
					session.setAttribute("certEmail", request.getParameter("email"));
					System.out.println("세션값 : " + session.getAttribute("certEmail"));
					forward = new ActionForward();
					forward.setPath("./joinMailCheck.jsp");
					forward.setRedirect(false);
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>"); // 자바스크립트 시작 태그
					out.println("alert('SMTP 서버 설정 문제 또는 서비스 문제 발생!')"); // 메세지는 작은따옴표('')로 표기해야함
					out.println("history.back()");
					out.println("</script>"); // 자바스크립트 끝 태그
				}

		return forward;
	}

}
