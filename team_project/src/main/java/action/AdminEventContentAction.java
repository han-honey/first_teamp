package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardEventContentService;
import svc.BoardNoticeContentService;
import vo.ActionForward;
import vo.BoardEventDTO;

public class AdminEventContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminEventContentAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pageNum = request.getParameter("page"); 
		
		BoardEventContentService boardEventContentService = new BoardEventContentService();
		BoardEventDTO board = boardEventContentService.getArticle(idx);
		
		
		request.setAttribute("board", board);
		request.setAttribute("page", pageNum);
		
		forward = new ActionForward();
		forward.setPath("./adminEventContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
