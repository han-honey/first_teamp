package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BoardNoticeContentService;
import svc.PaymentOrderContentService;
import vo.ActionForward;
import vo.PaymentDTO;

public class PaymentOrderContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PaymentOrderContentAction");
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession(false); // 저장된 세션값 없을 경우 false: null값 반환 / true: 새로운 세션 생성
		String id = (String)session.getAttribute("sessionId"); // 로그인 성공 시 저장한 memberId 값(세션) 변수에 저장
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pageNum = request.getParameter("page"); 
		
		PaymentOrderContentService paymentOrderContentService = new PaymentOrderContentService();
		PaymentDTO paymentDTO = paymentOrderContentService.getOrder(idx, id);
		
		
		request.setAttribute("paymentDTO", paymentDTO);
		request.setAttribute("page", pageNum);
		
		forward = new ActionForward();
		forward.setPath("./orderContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
