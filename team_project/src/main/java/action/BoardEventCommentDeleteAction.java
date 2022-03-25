package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardEventCommentProService;
import vo.ActionForward;

public class BoardEventCommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardEventCommentDeleteAction");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("c_idx"));
System.out.println("action에 넘어온 idx 값 : " + idx);
		BoardEventCommentProService boardEventCommentProService = new BoardEventCommentProService();
		int deleteCount = boardEventCommentProService.deleteComment(idx);
		System.out.println(deleteCount);
		if(deleteCount < 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 작성 실패!')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			//forward = new ActionForward();
			
			//forward.setPath("./EventContent.bo");
			//forward.setRedirect(false);
		}
		return forward;
	}

}
