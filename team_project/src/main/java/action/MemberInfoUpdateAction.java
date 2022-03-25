package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import svc.MemberInfoUpdateService;
import svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberInfoUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberInfoUpdateAction");
		ActionForward forward = null;
		
		HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("sessionId");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMember_id(id);
		memberDTO.setMember_phone(request.getParameter("phone"));
		memberDTO.setMember_email(request.getParameter("email"));
		memberDTO.setMember_marketing_agree(request.getParameter("marketing_agree"));
		
		MemberInfoUpdateService memberInfoUpdateService = new MemberInfoUpdateService();
		boolean isUpdateSuccess = memberInfoUpdateService.updateInfo(memberDTO);
		
		if(!isUpdateSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('회원정보 수정 실패!')"); // 메세지는 작은따옴표('')로 표기해야함
			out.println("history.back()");
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			forward = new ActionForward();
			forward.setPath("./MemberInfo.me");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
