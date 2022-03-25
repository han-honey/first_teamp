package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ReviewContentService;
import vo.ActionForward;
import vo.ReviewDTO;

public class ReviewModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewModifyFormAction");
		
		ActionForward forward = null;
		
		int review_idx = Integer.parseInt(request.getParameter("review_idx"));
		
		ReviewContentService reviewContentService = new ReviewContentService();
		ReviewDTO dto = reviewContentService.getArticle(review_idx);
		
		request.setAttribute("dto", dto);
		
		forward = new ActionForward();
		forward.setPath("./review_modify.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
