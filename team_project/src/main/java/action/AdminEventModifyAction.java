package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardEventModifyService;
import vo.ActionForward;
import vo.BoardEventDTO;

public class AdminEventModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminEventModifyAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardEventModifyService boardEventModifyService = new BoardEventModifyService();
		BoardEventDTO board = boardEventModifyService.getArticle(idx);
		
		System.out.println("setAttribute에 저장 =>" + board);
		
		request.setAttribute("board", board);
		
		forward = new ActionForward();
		forward.setPath("./adminEventModify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
