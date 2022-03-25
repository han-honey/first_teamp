package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PaymentCartDeleteService;
import svc.PaymentCartInsertService;
import svc.PaymentCartListService;
import vo.ActionForward;
import vo.CartDTO;

public class PaymentCartInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PaymentCartInsertAction - execute() 메서드 호출됨");
		
		HttpSession session = request.getSession(false);
		String sessionId = (String)session.getAttribute("sessionId");
		String product_code = request.getParameter("product_code");
		int product_amount = Integer.parseInt(request.getParameter("product_amount"));
		int product_price = Integer.parseInt(request.getParameter("product_price"));
		System.out.println(product_code);
		System.out.println(product_amount);
		System.out.println(product_price);
		
		CartDTO cartDTO = new CartDTO();
		cartDTO.setMember_id(sessionId);
		cartDTO.setProduct_code(product_code);
		cartDTO.setProduct_amount(product_amount);
		cartDTO.setProduct_price(product_price);
		
		ActionForward forward = null;
		
		int insertCount = 0;
		
		PaymentCartInsertService paymentCartInsertService  = new PaymentCartInsertService();
		insertCount = paymentCartInsertService.cartInsert(cartDTO); // 전체 게시물 수
		
		System.out.println("CartListAction - svc에서 넘겨받은 insertCount : " + insertCount);
		// -------------------------------------------------------------------------------------
		// 게시물 목록 조회 작업 완료 후 ActionForward 객체를 통해 뷰페이지(driver_list.jsp)로 포워딩
		// => 요청 서블릿 주소(BoardDriverList.bo)가 유지되어야 하고,
		//    request 객체가 유지되어야 하므로 Dispatcher 방식 포워딩 필요
		if(insertCount > 0) {
			forward = new ActionForward();
			forward.setPath("../product/BoardProductContent.bo");
			forward.setRedirect(false); // 생략 가능
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
