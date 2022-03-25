package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberLoginProService;
import vo.ActionForward;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginProAction");
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberLoginProService memberLoginProService = new MemberLoginProService();
		boolean isLoginSuccess = memberLoginProService.Login(id, pass);
		if(!isLoginSuccess) { 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('로그인 실패!')"); 
			out.println("history.back()");
			out.println("</script>"); 
		} else {
			memberLoginProService.LoginDate(id); //로그인 날짜 업데이트
			
			HttpSession session = request.getSession(true);
			session.setAttribute("sessionId", id);
			session.setMaxInactiveInterval(1800*48); //세션 유지 시간 1시간
			System.out.println("세션값 : " + session.getAttribute("sessionId"));
			
			
			forward = new ActionForward();
			forward.setPath("../main/Main.ma");
			forward.setRedirect(true); // Redirect 방식 포워딩
		}
		return forward;
	}

}
