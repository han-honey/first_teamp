package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PaymentCheckoutWriteFormService;
import svc.PaymentCheckoutWriteProService;
import vo.ActionForward;
import vo.AddressDTO;
import vo.CartDTO;
import vo.MemberDTO;
import vo.PaymentDTO;

public class PaymentCheckoutWriteProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PaymentCheckoutWriteProAction");
		ActionForward forward = null;
		
		HttpSession session = request.getSession(false);
		String id = (String)session.getAttribute("sessionId");
		
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setOrderer_name(request.getParameter("orderer_name"));
		System.out.println("주문자 ID : " + id);
		paymentDTO.setOrderer_id(id);
		paymentDTO.setReceiver_name(request.getParameter("receiver_name"));
		paymentDTO.setReceiver_phone(request.getParameter("receiver_phone"));
		paymentDTO.setReceiver_post(Integer.parseInt(request.getParameter("zipNo")));
		paymentDTO.setReceiver_address(request.getParameter("roadAddrPart1"));
		paymentDTO.setReceiver_extra_Address(request.getParameter("addrDetail"));
		paymentDTO.setOrder_product_code(request.getParameterValues("product_code"));
		String[] product_amountS = request.getParameterValues("product_amount");
		int[] prodcut_amount = new int[product_amountS.length];
		for(int i=0; i<product_amountS.length; i++) {
			prodcut_amount[i] += Integer.parseInt(product_amountS[i]);
		}
		paymentDTO.setOrder_product_amount(prodcut_amount);
//		paymentDTO.setOrder_coupon(request.getParameter(""));
		paymentDTO.setBefore_discount_price(Integer.parseInt(request.getParameter("price_sum")));
		paymentDTO.setAfter_discount_price(Integer.parseInt(request.getParameter("price_sum")));
//		paymentDTO.setOrder_status(Integer.parseInt(request.getParameter(""))); // 기본값 1 = 상품준비중
//		paymentDTO.setOrder_tracking_num(request.getParameter("")); // 이 단계에서 아님
		paymentDTO.setOrder_request_message(request.getParameter("order_request_message"));
//		paymentDTO.setOrder_return_refund_check(Integer.parseInt(request.getParameter(""))); // 기본값 1 = 반품(0), 환불(-1)		
		
		PaymentCheckoutWriteProService paymentWriteProService = new PaymentCheckoutWriteProService();
		
		boolean isCheck = paymentWriteProService.checkStockAmount(paymentDTO); // 재고 확인
		System.out.println(isCheck);
		int isSuccessOrder = 0;
		if(isCheck) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('재고가 부족합니다.\n재고량을 확인해주세요!')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			
		} else {
			
			isSuccessOrder = paymentWriteProService.insertOrderList(paymentDTO);
			
			if(isSuccessOrder < 1) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('주문이 실패 되었습니다\n주문정보를 확인해주세요.')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				
			} else {
				forward = new ActionForward();
				forward.setPath("/payment/OrderResult.pa");
				forward.setRedirect(false);
			}
			
		}
		
		return forward;
	}

	

}
