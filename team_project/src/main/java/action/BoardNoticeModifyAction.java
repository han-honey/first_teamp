package action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardNoticeModifyService;
import vo.ActionForward;
import vo.BoardDTO;

public class BoardNoticeModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardNoticeWriteProAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String page = request.getParameter("page");
		BoardNoticeModifyService boardNoticeModifyService = new BoardNoticeModifyService();
		BoardDTO board = boardNoticeModifyService.getArticle(idx);
		
		System.out.println("setAttribute에 저장 =>" + board);
		
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		forward = new ActionForward();
		forward.setPath("./noticeModify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
