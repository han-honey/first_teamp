package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PaymentCartDeleteService;
import svc.PaymentCartListService;
import vo.ActionForward;
import vo.CartDTO;

public class PaymentCartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PaymentCartDeleteAction - execute() 메서드 호출됨");
		
		HttpSession session = request.getSession(false);
		String sessionId = (String)session.getAttribute("sessionId");
		String product_codes = request.getParameter("product_code");
		String[] product_code = product_codes.split("/");
		System.out.println("product_code"+product_code);
		ActionForward forward = null;
		
		int deleteCount = 0;
		
		PaymentCartDeleteService paymentCartDeleteService  = new PaymentCartDeleteService();
		deleteCount = paymentCartDeleteService.cartDelete(sessionId,product_code); // 전체 게시물 수
		
		System.out.println("CartListAction - svc에서 넘겨받은 deleteCount : " + deleteCount);
		// -------------------------------------------------------------------------------------
		// 게시물 목록 조회 작업 완료 후 ActionForward 객체를 통해 뷰페이지(driver_list.jsp)로 포워딩
		// => 요청 서블릿 주소(BoardDriverList.bo)가 유지되어야 하고,
		//    request 객체가 유지되어야 하므로 Dispatcher 방식 포워딩 필요
		if(deleteCount > 0) {
			forward = new ActionForward();
			forward.setPath("../payment/CartList.pa");
			forward.setRedirect(true); // 생략 가능
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('장바구니 삭제 실패!\n새로고침 해주세요')");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
