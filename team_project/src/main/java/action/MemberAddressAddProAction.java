package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberAddressProService;
import svc.MemberAddressListService;
import vo.ActionForward;
import vo.AddressDTO;

public class MemberAddressAddProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberAddressAddProAction");
		ActionForward forward = null;
		HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("sessionId");
		
		int address_post = Integer.parseInt(request.getParameter("address_post"));
		String address = request.getParameter("address");
		String extra_address = request.getParameter("extra_address");
		String request_message = request.getParameter("request_message");
		int address_default = 0; //기본 주소록 여부의 null값을 확인해줌
		if(request.getParameter("address_default")!= null) {
			address_default = Integer.parseInt(request.getParameter("address_default")); 
		} else {
			address_default = 0;
		}
		
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setMember_id(id);
		addressDTO.setAddress_post(address_post);
		addressDTO.setAddress(address);
		addressDTO.setExtra_address(extra_address);
		addressDTO.setRequest_message(request_message);
		addressDTO.setAddress_default(address_default);
		
		
		MemberAddressProService memberAddressProService = new MemberAddressProService();
		
		boolean isInsertAddress = false;
		if(request.getParameter("address_idx") == null) { // 배송지 신규 추가인 경우
			isInsertAddress = memberAddressProService.insertMemberAddress(addressDTO);
		} else { // 배송지 수정인 경우
			int address_idx = Integer.parseInt(request.getParameter("address_idx"));
			System.out.println(address_idx);
			addressDTO.setAddress_idx(address_idx);
			isInsertAddress = memberAddressProService.updateMemberAddress(addressDTO);
		}
		
		if(isInsertAddress) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>"); // 자바스크립트 시작 태그
		out.println("opener.location.reload();");
		out.println("window.close();");
		out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('배송지 등록 실패! 다시 시도해주세요.')");
			out.println("opener.location.reload();");
			out.println("window.close();");
			out.println("</script>"); // 자바스크립트 끝 태그
		}
		return forward;
		
	}

}
