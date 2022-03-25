package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminProductModifyProService;
import vo.ActionForward;
import vo.ProductBoardDTO;

public class AdminProductModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("AdminProductModifyProAction");
		
		ActionForward forward = null;
		
		ProductBoardDTO dto = new ProductBoardDTO();
		dto.setProduct_code(request.getParameter("product_code"));
		System.out.println(request.getParameter("product_code"));
		
		dto.setProduct_stock(Integer.parseInt(request.getParameter("product_stock")));
		System.out.println(request.getParameter("product_stock"));

		AdminProductModifyProService adminProductModifyProService = new AdminProductModifyProService();
		
		boolean isModifySuccess = adminProductModifyProService.modifyArticle(dto);
		
		if(!isModifySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setPath("AdminProductList.ad?product_code="+dto.getProduct_code());
			forward.setRedirect(true);
		}
		return forward;
	}

}
