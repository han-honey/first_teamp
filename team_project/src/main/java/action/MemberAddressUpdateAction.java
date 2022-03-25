package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberAddressListService;
import vo.ActionForward;
import vo.AddressDTO;

public class MemberAddressUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberAddressUpdateAction");
		ActionForward forward = null;
		
		int address_idx = Integer.parseInt(request.getParameter("address_idx"));
		MemberAddressListService addressListService = new MemberAddressListService();
		
		AddressDTO addressDTO = addressListService.selectMemberAddressOne(address_idx);
		
		request.setAttribute("addressDTO", addressDTO);
		
		forward = new ActionForward();
		forward.setPath("./addressUpdate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
