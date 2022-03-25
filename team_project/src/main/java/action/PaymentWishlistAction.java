package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PaymentWishlistService;
import vo.ActionForward;
import vo.WishlistDTO;

public class PaymentWishlistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WishlistAction - execute() 메서드 호출");
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession(false); // 저장된 세션값 없을 경우 false: null값 반환 / true: 새로운 세션 생성
		String member_id = (String)session.getAttribute("sessionId"); // 로그인 성공 시 저장한 memberId 값(세션) 변수에 저장
//		String member_id = "admin3";
		System.out.println(member_id);
		
		PaymentWishlistService paymentWishlistService = new PaymentWishlistService();
		ArrayList<WishlistDTO> wishlist = paymentWishlistService.getWishlist(member_id); // svc에 memverId 보내주기
		
		System.out.println("WishlistAction - execute : svc에서 넘겨받은 wishlist : " + wishlist);
		
		request.setAttribute("wishlist", wishlist);
		
		forward = new ActionForward();
		forward.setPath("./wishlist.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
