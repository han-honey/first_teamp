package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PaymentOrderListService;
import svc.PaymentOrderResultService;
import vo.ActionForward;
import vo.PaymentDTO;

public class PaymentOrderResultAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PaymentOrderListAction");
		ActionForward forward = null;
		HttpSession session = request.getSession(false); // 저장된 세션값 없을 경우 false: null값 반환 / true: 새로운 세션 생성
		String id = (String)session.getAttribute("sessionId"); // 로그인 성공 시 저장한 sessionId 값(세션) 변수에 저장

		PaymentOrderResultService orderResultService = new PaymentOrderResultService();
		PaymentDTO orderResult = orderResultService.selectOrderResult(id);
		
		if(orderResult == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('주문 내역이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
		} else {
			
			forward = new ActionForward();
			request.setAttribute("orderResult", orderResult);
			
			forward.setPath("./checkout_result.jsp");
			forward.setRedirect(false);
		}
		
		
		return forward;
	}

}
