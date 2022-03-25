package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardQnAContentService;
import vo.ActionForward;
import vo.QnADTO;

public class AdminQnAContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminQnAContentAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pageNum = request.getParameter("page"); 
		
		BoardQnAContentService boardQnAContentService = new BoardQnAContentService();
		QnADTO board = boardQnAContentService.getArticle(idx);
		
		
		request.setAttribute("board", board);
		request.setAttribute("page", pageNum);
		
		forward = new ActionForward();
		forward.setPath("./adminQnAContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
