package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewDeleteProService;
import vo.ActionForward;
import vo.ReviewDTO;

public class ReviewDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewDeleteProAction");
		
		ActionForward forward = null;
		
		int review_idx = Integer.parseInt(request.getParameter("review_idx")) ;
		
		
		ReviewDeleteProService reviewDeleteProService = new ReviewDeleteProService();
			
			boolean isDeleteSuccess = reviewDeleteProService.removeArticle(review_idx);
			
			if(!isDeleteSuccess) { 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>"); 
				out.println("alert('삭제 권한이 없습니다')"); 
				out.println("history.back()");
				out.println("</script>"); 
			} else { 
				forward = new ActionForward();
				forward.setPath("./BoardProductList.bo");
				forward.setRedirect(true);
			}
		
		return forward;
}

}
