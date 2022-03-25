package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberCheckPasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CheckPasswordAction");
		ActionForward forward = null;
		
		HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("sessionId");
		String pass = request.getParameter("pass");
		
		MemberLoginProService memberLoginProService = new MemberLoginProService();
		boolean correctPassword = memberLoginProService.Login(id,pass);
		
		if(correctPassword) {
			MemberInfoService memberInfoService = new MemberInfoService();
			MemberDTO memberDTO = memberInfoService.selectMemberInfo(id);
			
			request.setAttribute("memberDTO", memberDTO);
			
			forward = new ActionForward();
			forward.setPath("./updateInfoForm.jsp");
			forward.setRedirect(false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('패스워드 오류! 수정권한이 없습니다.')"); 
			out.println("history.back()");
			out.println("</script>"); 
		}
		
		return forward;
	
	}

}
