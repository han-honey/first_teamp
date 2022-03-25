package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BoardEventDeleteProService;
import vo.ActionForward;

public class BoardEventDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminEventDeleteProAction");
		
		ActionForward forward = null;
		
		
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("sessionId");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		BoardEventDeleteProService boardEventDeleteProService = new BoardEventDeleteProService();
		boolean isArticleWriter = boardEventDeleteProService.isArticleWriter(id);
		
		if(!isArticleWriter) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
		} else {
			boolean isDeleteSuccess = boardEventDeleteProService.removeArticle(idx);
			
			if(!isDeleteSuccess) { 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>"); 
				out.println("alert('글 삭제 실패!')"); 
				out.println("history.back()");
				out.println("</script>"); 
			} else { 
				forward = new ActionForward();
				forward.setPath("EventList.bo?page=" + request.getParameter("page"));
				forward.setRedirect(true);
			}
		}
		
		return forward;
	}

}
