package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardEventCommentProService;
import svc.BoardEventContentService;
import svc.BoardNoticeContentService;
import vo.ActionForward;
import vo.BoardEventCommentDTO;
import vo.BoardEventDTO;

public class BoardEventContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardEventContentAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pageNum = request.getParameter("page"); 
		
		BoardEventContentService boardEventContentService = new BoardEventContentService();
		BoardEventDTO board = boardEventContentService.getArticle(idx);
		
		BoardEventCommentProService boardEventCommentProService = new BoardEventCommentProService();
		ArrayList<BoardEventCommentDTO> boardEventCommentDTO = boardEventCommentProService.getEventCommentList(idx);
		System.out.println("boardEventCommentDTO : " + boardEventCommentDTO);
		
		request.setAttribute("board", board);
		request.setAttribute("page", pageNum);
		if(boardEventCommentDTO != null) {
			request.setAttribute("boardEventCommentDTO", boardEventCommentDTO);
		}
		
		forward = new ActionForward();
		forward.setPath("./eventContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
