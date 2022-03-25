package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PaymentCheckoutWriteFormService;
import vo.ActionForward;
import vo.AddressDTO;
import vo.CartDTO;
import vo.MemberDTO;

public class PaymentCheckoutWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PaymentCheckoutWriteFormAction");
		ActionForward forward = null;
		HttpSession session = request.getSession(false);
		String id = (String)session.getAttribute("sessionId");
		String product_codes = request.getParameter("product_code");
		String[] product_code = product_codes.split("/");
		
		
		PaymentCheckoutWriteFormService paymentWriteFormService = new PaymentCheckoutWriteFormService();
		
		ArrayList<CartDTO> cartList = paymentWriteFormService.selectCartList(id, product_code);
		
		if(cartList == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('장바구니가 비었습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
		} else {
			
			MemberDTO memberDTO = paymentWriteFormService.getMemberInfo(id);
			ArrayList<AddressDTO> addressList = paymentWriteFormService.getAddressList(id);
			request.setAttribute("cartList", cartList);
			request.setAttribute("memberDTO", memberDTO);
			request.setAttribute("addressList", addressList);
			
			forward = new ActionForward();
			
			forward.setPath("./checkout.jsp");
			forward.setRedirect(false);
		}
		
		
		return forward;
	}

	

}
