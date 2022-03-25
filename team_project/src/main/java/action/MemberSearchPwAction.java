package action;

import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberEmailSendService;
import svc.MemberInfoService;
import svc.MemberInfoUpdateService;
import svc.MemberSearchIdPwService;
import vo.ActionForward;
import vo.Email_certificationDTO;
import vo.MemberDTO;

public class MemberSearchPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberSearchPwAction");
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		MemberSearchIdPwService memberSearchIdPwService = new MemberSearchIdPwService();
		boolean isPwExist = memberSearchIdPwService.searchPw(name, email, id); //해당정보의 회원이 있는지 확인
		
		if (isPwExist) { //회원정보 일치 시, 임시비밀번호 생성
			char[] codeTable = {
					'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
					'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
					'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
				};
			StringBuffer buffer = new StringBuffer(50);
			
			Random r = new Random();
			
			// for문을 사용하여 인증 코드 길이만큼 반복
			for(int i = 0; i <= 8; i++) {
				// java.util.Random 클래스의 인스턴스 생성 및 nextInt() 메서드 호출하여 난수 생성
				// 단, 생성되는 난수의 범위는 코드값이 저장된 배열의 크기로 한정
				// => 인덱스에 해당하는 배열 위치의 코드값을 버퍼에 저장
				int rNum = r.nextInt(codeTable.length); // 0 ~ 배열 크기-1 까지 범위 내의 난수를 발생시켜 변수에 저장 
				buffer.append(codeTable[rNum]); // 발생된 난수를 배열 인덱스로 활용하여 추출된 코드 1개를 버퍼에 저장
			}
			
			// 생성된 인증코드를 문자열로 변환하여 변수에 저장
			String tempPass = buffer.toString();
			System.out.println("임시비번 : " + tempPass);
			
			Email_certificationDTO certificationDTO = new Email_certificationDTO();
			certificationDTO.setMember_id(id);
			certificationDTO.setMember_email(email);
			certificationDTO.setTemp_password(tempPass);
			
			MemberEmailSendService memberEmailCheckService = new MemberEmailSendService();
			boolean isSendMail = memberEmailCheckService.sendMail(certificationDTO);
			
			if(isSendMail) { //임시 비밀번호를 메일로 전송
				MemberDTO memberDTO = new MemberDTO(); //memberDTO로 다시 담음
				memberDTO.setMember_id(id);
				memberDTO.setMember_password(tempPass);
				
				MemberInfoUpdateService memberInfoUpdateService = new MemberInfoUpdateService();
				boolean isUpdatePw = memberInfoUpdateService.updatePassword(memberDTO);
				if(isUpdatePw) {
				request.setAttribute("memberDTO", memberDTO);
				forward = new ActionForward();
				forward.setPath("./searchPwResult.jsp?pwExist=true");
				forward.setRedirect(false);
				}
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>"); // 자바스크립트 시작 태그
				out.println("alert('SMTP 서버 설정 문제 또는 서비스 문제 발생!')"); // 메세지는 작은따옴표('')로 표기해야함
				out.println("history.back()");
				out.println("</script>"); // 자바스크립트 끝 태그
			}
		} else {
			
			forward = new ActionForward();
			forward.setPath("./searchPwResult.jsp?pwExist=false");
			forward.setRedirect(false);
		}
		
		return forward;
	
	}

}
