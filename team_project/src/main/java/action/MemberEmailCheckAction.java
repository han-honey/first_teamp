package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberEmailCheckService;
import vo.ActionForward;

public class MemberEmailCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("MemberEmailCheckAction");
		
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("certEmail");
		int cert_num = Integer.parseInt(request.getParameter("cert"));
		
		MemberEmailCheckService emailCheckService = new MemberEmailCheckService();
		boolean isCert = emailCheckService.checkMail(email, cert_num);
		
		forward = new ActionForward();
		forward.setPath("./joinMailCheck.jsp?isCert="+isCert);
		forward.setRedirect(true);
		return forward;
	}

}
