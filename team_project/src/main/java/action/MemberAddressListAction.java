package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberAddressListService;
import vo.ActionForward;
import vo.AddressDTO;

public class MemberAddressListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberAddressListAction");
		ActionForward forward = null;
		
		HttpSession session = request.getSession(false); 
		String id = (String)session.getAttribute("sessionId"); 
		
		MemberAddressListService memberAddressListService = new MemberAddressListService();
		ArrayList<AddressDTO> addressList = memberAddressListService.selectMemberAddress(id);
		
		request.setAttribute("addressList", addressList);
		
		forward = new ActionForward();
		forward.setPath("./addressList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
