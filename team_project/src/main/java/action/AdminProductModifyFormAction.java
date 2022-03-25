package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductBoardDAO;
import svc.BoardProductContentService;
import vo.ActionForward;
import vo.ProductBoardDTO;

public class AdminProductModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("AdminProductModifyFormAction");
		
		ActionForward forward = null;
		
		String product_code = request.getParameter("product_code");
		
		BoardProductContentService boardProductContentService = new BoardProductContentService();
		ProductBoardDTO dto = boardProductContentService.getArticle(product_code);
		
		request.setAttribute("dto", dto);
		
		forward = new ActionForward();
		forward.setPath("./admin_product_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
