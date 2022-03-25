package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PaymentWishlistAddService;
import vo.ActionForward;
import vo.WishlistDTO;

public class PaymentWishlistAddAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WishlistAddAction - execute() 메서드 호출");
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession(false); // 저장된 세션값 없을 경우 false: null값 반환 / true: 새로운 세션 생성
		String member_id = (String)session.getAttribute("sessionId"); // 로그인 성공 시 저장한 memberId 값(세션) 변수에 저장
		String product_code = request.getParameter("product_code");
		
		if (member_id == null) { // 로그인하지 않았을 경우 다음 단계 진행 막음 + 팝업창
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스입니다.')");
			out.println("history.back();");
			out.println("</script>");
			
		} else {
			WishlistDTO wishlistDTO = new WishlistDTO();
			wishlistDTO.setMember_id(member_id);
			wishlistDTO.setProduct_code(product_code); // 상품페이지에서 hidden으로 넘겨받기
			// dto에 담기 끝
			System.out.println("WishlistAddAction - dto에 담은거 확인 : " + wishlistDTO);
			
			PaymentWishlistAddService paymentWishlistAddService = new PaymentWishlistAddService();
			int isWishlistAddSuccess = paymentWishlistAddService.isWishlistAddSuccess(wishlistDTO); // memberId, productIdx 담은 dto를 svc 메서드로 보내기
			
			if (isWishlistAddSuccess == 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('위시리스트 담기 실패!')");
				out.println("history.back();");
				out.println("</script>");
				
			} else if (isWishlistAddSuccess == -1) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('중복!')");
				out.println("history.back();");
				out.println("</script>");
				
			} else { // 1
//				forward = new ActionForward();
//				forward.setPath("../product/BoardProductContent.bo?product_code=" + product_code);
//				forward.setRedirect(true);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		return forward;
	}

}
