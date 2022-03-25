package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.MemberDTO;

public class AdminMemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminMemberListAction");
		ActionForward forward = null;
		
		MemberInfoService memberInfoService = new MemberInfoService();
		ArrayList<MemberDTO> memberList = memberInfoService.selectMemberList();
		
		request.setAttribute("memberList", memberList);
		
		forward = new ActionForward();
		forward.setPath("./management/member_management.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
