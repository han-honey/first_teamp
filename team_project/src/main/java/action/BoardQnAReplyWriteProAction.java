package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BoardQnAReplyWriteProService;
import vo.ActionForward;
import vo.QnADTO;
import vo.QnAReplyDTO;

public class BoardQnAReplyWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardQnAReplyWriteProAction");
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		
		QnAReplyDTO reDTO = new QnAReplyDTO();
		reDTO.setReply_ref(Integer.parseInt(request.getParameter("idx")));
		reDTO.setReply_member_id((String)session.getAttribute("sessionId"));
		reDTO.setReply_content(request.getParameter("replyContent"));
		
		System.out.println((String)request.getAttribute("sessionId"));
		
		BoardQnAReplyWriteProService boardQnAReplyWriteProService = new BoardQnAReplyWriteProService(); 
		boolean isMember = boardQnAReplyWriteProService.isMember(reDTO);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(!isMember) {
			out.println("<script>"); 
			out.println("alert('로그인후 이용가능!')"); 
			out.println("location.href='../member/login.jsp'");
			out.println("</script>"); 
		} else {
			
			boolean isWriteSuccess = boardQnAReplyWriteProService.registReply(reDTO);
			
			if(!isWriteSuccess) {
				out.println("<script>"); 
				out.println("alert('글 등록 실패!')"); 
				out.println("history.back()");
				out.println("</script>"); 
			} else {
				forward = new ActionForward();
				forward.setPath("./QnAList.bo?idx=");
				forward.setRedirect(true);
			}
		}
		
		return forward;
	}

}
