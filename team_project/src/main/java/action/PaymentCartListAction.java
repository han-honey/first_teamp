package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PaymentCartListService;
import vo.ActionForward;
import vo.CartDTO;

public class PaymentCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PaymentCartListAction - execute() 메서드 호출됨");
		
		HttpSession session = request.getSession(false);
		String sessionId = (String)session.getAttribute("sessionId");
		
		ActionForward forward = null;
		
		PaymentCartListService paymentCartListService  = new PaymentCartListService();
		ArrayList<CartDTO> cartList = paymentCartListService.cartList(sessionId); // cart db에 product테이블에서 가져온 정보 저장만 하기
		System.out.println("CartListAction - svc에서 넘겨받은 cartList : " + cartList);
		int cartTotalPrice = paymentCartListService.cartTotalPrice(sessionId);
		request.setAttribute("cartList", cartList);
		request.setAttribute("cartTotalPrice", cartTotalPrice);
		// -------------------------------------------------------------------------------------
		// 게시물 목록 조회 작업 완료 후 ActionForward 객체를 통해 뷰페이지(driver_list.jsp)로 포워딩
		// => 요청 서블릿 주소(BoardDriverList.bo)가 유지되어야 하고,
		//    request 객체가 유지되어야 하므로 Dispatcher 방식 포워딩 필요
		forward = new ActionForward();
		forward.setPath("../payment/cartList.jsp");
		forward.setRedirect(false); // 생략 가능
		
		return forward;
	}

}
