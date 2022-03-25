package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("MemberInfoAction");
		
		HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("sessionId");
		
		MemberInfoService memberInfoService = new MemberInfoService();
		MemberDTO memberDTO = memberInfoService.selectMemberInfo(id);
		
		request.setAttribute("memberDTO", memberDTO);
		
		forward = new ActionForward();
		forward.setPath("./memberInfo.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
