package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminProductDeleteProService;
import vo.ActionForward;

public class AdminProductDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminProductDeleteProAction");
	
		ActionForward forward = null;
		
		int product_idx = Integer.parseInt(request.getParameter("product_idx"));
		
		AdminProductDeleteProService adminProductDeleteProService = new AdminProductDeleteProService();
		
		
		boolean isDeleteSuccess = adminProductDeleteProService.removeArticle(product_idx);
		
		if(!isDeleteSuccess) { 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('글 삭제 실패!')"); 
			out.println("history.back()");
			out.println("</script>"); 
		} else { 
			
			forward = new ActionForward();
			forward.setPath("AdminProductList.ad?product_idx=" + product_idx);
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
