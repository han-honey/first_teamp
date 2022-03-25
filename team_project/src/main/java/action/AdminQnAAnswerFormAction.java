package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardQnAAnswerFormService;
import vo.ActionForward;
import vo.QnADTO;

public class AdminQnAAnswerFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminQnAAnswerFormAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardQnAAnswerFormService boardQnAAnswerFormService = new BoardQnAAnswerFormService();
		QnADTO board = boardQnAAnswerFormService.getArticle(idx);
		
		System.out.println("setAttribute에 저장 =>" + board);
		
		request.setAttribute("board", board);
		
		forward = new ActionForward();
		forward.setPath("./adminQnAAnswerForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
