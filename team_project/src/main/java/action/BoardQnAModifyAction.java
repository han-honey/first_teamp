package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardQnAModifyService;
import vo.ActionForward;
import vo.QnADTO;

public class BoardQnAModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardQnAModifyAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardQnAModifyService boardQnAModifyService = new BoardQnAModifyService();
		QnADTO board = boardQnAModifyService.getArticle(idx);
		
		System.out.println("setAttribute에 저장 =>" + board);
		
		request.setAttribute("board", board);
		
		forward = new ActionForward();
		forward.setPath("./QnAModify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
