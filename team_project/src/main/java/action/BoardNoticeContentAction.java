package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardNoticeContentService;
import vo.ActionForward;
import vo.BoardDTO;

public class BoardNoticeContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardNoticeContentAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pageNum = request.getParameter("page"); 
		
		BoardNoticeContentService boardNoticeContentService = new BoardNoticeContentService();
		BoardDTO board = boardNoticeContentService.getArticle(idx);
		System.out.println(board);
		
		request.setAttribute("board", board);
		request.setAttribute("page", pageNum);
		
		forward = new ActionForward();
		forward.setPath("./noticeContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
