package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardEventCommentProService;
import vo.ActionForward;

public class BoardEventCommentProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardEventCommentProAction");
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String text = request.getParameter("text");
		int idx = Integer.parseInt(request.getParameter("idx"));

		BoardEventCommentProService boardEventCommentProService = new BoardEventCommentProService();
		int addComment = boardEventCommentProService.addComment(id, text, idx);
		int c_idx = addComment;
		if(addComment < 0) {
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
			out.print("{");
			out.print("\"id\" : \""+id+"\", \"text\" : \"" + text + "\", \"c_idx\" : \"" + c_idx + "\"");	
			out.print("}");
			/*out.println("<tr>");
			out.println("<td align=\"center\" width=\"150px\">"+id+"</td>");
			out.println("<td align=\"left\" width=\"600px\" colspan=\"3\">"+text+"</td>");
			out.println("<td align=\"left\" width=\"150px\" colspan=\"3\">2022-03-20</td>");
			out.println("<td><input type=\"button\" value=\"삭제\" id=\"del\" onclick=\"delete('this')\"></td>");
			out.println("</tr>");	*/	
			
			//forward = new ActionForward();
			
			//forward.setPath("./EventContent.bo");
			//forward.setRedirect(false);
		}
		return forward;
	}

}
