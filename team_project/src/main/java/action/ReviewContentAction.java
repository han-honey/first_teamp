package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewContentService;
import vo.ActionForward;
import vo.ReviewDTO;

public class ReviewContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewContentAction");
	
		ActionForward forward = null;
		
//		HttpSession session = request.getSession();
//		String member_id = (String)request.getAttribute("sessionId");
		
		int review_idx = Integer.parseInt(request.getParameter("review_idx"));
		
		System.out.println(review_idx);
		ReviewContentService reviewContentService = new ReviewContentService();
		ReviewDTO dto = reviewContentService.getArticle(review_idx);
		//dto.setMember_id(member_id);
		request.setAttribute("dto", dto);
		
		System.out.println("--------");
//		System.out.println(member_id);
		System.out.println(dto.getMember_id());
		System.out.println();
		
		forward = new ActionForward();
//		forward.setPath("./review_details.jsp?member_id=" + dto.getMember_id());
		//forward.setPath("./review_details.jsp?member_id=" + member_id);
		forward.setPath("./review_details.jsp");
		forward.setRedirect(false);
		return forward;
	
	}

}
