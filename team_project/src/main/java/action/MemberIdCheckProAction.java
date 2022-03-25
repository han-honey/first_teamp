package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberIdCheckProService;
import vo.ActionForward;

public class MemberIdCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberIdCheckProAction");
		
		ActionForward forward = null;
		
		String id2 = request.getParameter("id2");
		System.out.println("id2 = " + id2);
		
		MemberIdCheckProService memberIdCheckProService = new MemberIdCheckProService();
		boolean isDuplicate = memberIdCheckProService.isDuplicate(id2);
		
		if(isDuplicate) {//중복아이디 있는 경우
			forward = new ActionForward();
			forward.setPath("./idCheck.jsp?duplicate_id=duplicate");
			forward.setRedirect(false); // Redirect 방식 포워딩
		} else {
			forward = new ActionForward();
			forward.setPath("./idCheck.jsp?duplicate_id=" + id2);
			forward.setRedirect(false); // Redirect 방식 포워딩
		}
		
		return forward;
	}

}















