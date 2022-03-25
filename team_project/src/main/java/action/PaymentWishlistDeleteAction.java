package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PaymentWishlistDeleteService;
import svc.PaymentWishlistService;
import svc.PaymentWishlistAddService;
import vo.ActionForward;
import vo.WishlistDTO;

public class PaymentWishlistDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WishlistDeleteAction - execute() 메서드 호출");
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession(false); // 저장된 세션값 없을 경우 false: null값 반환 / true: 새로운 세션 생성
		String member_id = (String)session.getAttribute("sessionId"); // 로그인 성공 시 저장한 memberId 값(세션) 변수에 저장
		String product_code = request.getParameter("product_code"); // hidden 태그로 전달받은 product_code 변수에 저장(상품코드거나 ALLDELETE)
				
		WishlistDTO wishlistDTO = new WishlistDTO();
		wishlistDTO.setMember_id(member_id);
		wishlistDTO.setProduct_code(product_code);
		System.out.println("WishlistDeleteAction - product_code 값 확인하기 : " + product_code);
		
		PaymentWishlistDeleteService paymentWishlistDeleteService = new PaymentWishlistDeleteService();
		boolean isWishlistDeleteSuccess = paymentWishlistDeleteService.deleteWishlist(wishlistDTO);
		
		if (!isWishlistDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('위시리스트 삭제 실패!')");
			out.println("history.back();");
			out.println("</script>");
			
		} else {
			forward = new ActionForward();
			forward.setPath("./Wishlist.pa");
			forward.setRedirect(true);
		}
		
		return forward;
	}

	

}
