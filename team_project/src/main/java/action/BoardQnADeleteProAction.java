package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BoardNoticeDeleteProService;
import svc.BoardQnADeleteProService;
import vo.ActionForward;

public class BoardQnADeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardQnADeleteProAction");
		
		ActionForward forward = null;
		
		
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("sessionId");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		BoardQnADeleteProService boardQnADeleteProService = new BoardQnADeleteProService();
		boolean isArticleWriter = boardQnADeleteProService.isArticleWriter(id);
		
		if(!isArticleWriter) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
		} else {
			boolean isDeleteSuccess = boardQnADeleteProService.removeArticle(idx);
			
			if(!isDeleteSuccess) { 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>"); 
				out.println("alert('글 삭제 실패!')"); 
				out.println("history.back()");
				out.println("</script>"); 
			} else { 
				forward = new ActionForward();
				forward.setPath("./QnAList.bo?page=" + request.getParameter("page"));
				forward.setRedirect(true);
			}
		}
		
		return forward;
	}

}
