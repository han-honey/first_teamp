package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberSearchIdPwService;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberSearchIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberSearchIdAction");
		ActionForward forward = null;
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		MemberDTO memberDTO = new MemberDTO();
		MemberSearchIdPwService memberSearchIdService = new MemberSearchIdPwService();
		memberDTO = memberSearchIdService.searchId(name, email);
		
			request.setAttribute("memberDTO", memberDTO);
			forward = new ActionForward();
			forward.setPath("./searchIdResult.jsp");
			forward.setRedirect(false);
		
			return forward;
	}

}
