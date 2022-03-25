package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardEventModifyService;
import vo.ActionForward;
import vo.BoardEventDTO;

public class BoardEventModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardEventModifyAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String page = request.getParameter("page");
		
		BoardEventModifyService boardEventModifyService = new BoardEventModifyService();
		BoardEventDTO board = boardEventModifyService.getArticle(idx);
		
		System.out.println("setAttribute에 저장 =>" + board);
		
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		forward = new ActionForward();
		forward.setPath("./eventModify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
