package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardQnAContentService;
import svc.BoardQnAReplyListService;
import vo.ActionForward;
import vo.QnADTO;
import vo.QnAReplyDTO;

public class BoardQnAContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardQnAContentAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pageNum = request.getParameter("page"); 
		
		BoardQnAContentService boardQnAContentService = new BoardQnAContentService();
		QnADTO board = boardQnAContentService.getArticle(idx);
		
		BoardQnAReplyListService boardQnAReplyListService = new BoardQnAReplyListService();
		int replyListCount = boardQnAReplyListService.getQnAReplyListCount(idx);
		ArrayList<QnAReplyDTO> replyList = boardQnAReplyListService.getQnAReplyList(idx);
		
		request.setAttribute("board", board);
		request.setAttribute("page", pageNum);
		request.setAttribute("replyList", replyList);
		
		forward = new ActionForward();
		forward.setPath("./QnAContent.jsp?replyCount=" + replyListCount);
		forward.setRedirect(false);
		
		return forward;
	}

}
