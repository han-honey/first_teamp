package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import svc.MemberDeleteProService;
import vo.ActionForward;

public class MemberDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberDeleteProAction");
		ActionForward forward = null;
		
		HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("sessionId");
		
		MemberDeleteProService memberDeleteProService = new MemberDeleteProService();
		boolean isDeleteSuccess = memberDeleteProService.deleteMember(id);
		
		if(!isDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('회원 탈퇴 실패!')"); // 메세지는 작은따옴표('')로 표기해야함
			out.println("history.back()");
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			session.invalidate(); //회원 세션값 삭제
			forward = new ActionForward();
			forward.setPath("../main/Main.ma");
			forward.setRedirect(true);
		}
		return forward;
		
	}

}
