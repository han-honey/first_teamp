package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoUpdateService;
import svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberChangePasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberChangePasswordAction");
		ActionForward forward = null;
		
		HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("sessionId");
		String old_pass = request.getParameter("old_pass");
		String new_pass = request.getParameter("new_pass");
		System.out.println("아이디" + id);
		System.out.println("기존 비번 : " +old_pass);
		System.out.println("새 비번 : " +new_pass);
		MemberLoginProService loginProService = new MemberLoginProService();
		boolean isPassTrue = loginProService.Login(id, old_pass);
		System.out.println("비밀번호 일치 여부 : " + isPassTrue);

		if(isPassTrue) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setMember_id(id);
			memberDTO.setMember_password(new_pass);
			MemberInfoUpdateService infoUpdateService = new MemberInfoUpdateService();
			infoUpdateService.updatePassword(memberDTO);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('비밀번호가 성공적으로 변경되었습니다.')"); 
			out.println("</script>"); 
			forward = new ActionForward();
			forward.setPath("./MemberInfo.me");
			forward.setRedirect(false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('비밀번호를 정확하게 입력해주세요')"); 
			out.println("history.back()");
			out.println("</script>"); 
		}
		return forward;
	}

}
