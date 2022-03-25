package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardProductContentService;
import vo.ActionForward;
import vo.ProductBoardDTO;

public class AdminProductContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		String num = request.getParameter("num");
		//String pageNum = request.getParameter("page"); 
		
		BoardProductContentService boardProductContentService = new BoardProductContentService();
		ProductBoardDTO dto = boardProductContentService.getArticle(num);
		
		
		request.setAttribute("dto", dto);
//		request.setAttribute("page", pageNum);

		forward = new ActionForward();
		forward.setPath("./productDetails.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}














