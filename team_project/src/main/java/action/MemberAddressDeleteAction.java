package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberAddressProService;
import vo.ActionForward;

public class MemberAddressDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberAddressDeleteAction");
		ActionForward forward = null;
		
		int address_idx = Integer.parseInt(request.getParameter("address_idx"));
		
		MemberAddressProService memberAddressProService = new MemberAddressProService();
		boolean isDeleteAddress = memberAddressProService.deleteMemberAddress(address_idx);
		
		if(isDeleteAddress) {
			forward = new ActionForward();
			forward.setPath("./MemberAddressList.me");
			forward.setRedirect(false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('배송지 삭제 실패!')"); // 메세지는 작은따옴표('')로 표기해야함
			out.println("history.back()");
			out.println("</script>"); // 자바스크립트 끝 태그
		}
		return forward;
	}

}
