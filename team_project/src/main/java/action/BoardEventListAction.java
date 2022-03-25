package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardEventCommentProService;
import svc.BoardEventListService;
import svc.BoardNoticeListService;
import vo.ActionForward;
import vo.BoardDTO;
import vo.BoardEventCommentDTO;
import vo.BoardEventDTO;
import vo.PageInfo;

public class BoardEventListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EventBoardListAction");
		
		ActionForward forward = null;
		
		int pageNum = 1; 
		
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		BoardEventListService boardEventListService = new BoardEventListService();
		
		int listCount = boardEventListService.getListCount(); 
		
		int listLimit = 5;
		int pageLimit = 5; 
		
		ArrayList<BoardEventDTO> articleList = 
				boardEventListService.getArticleList(pageNum, listLimit);
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);	
		
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		
		int endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		forward = new ActionForward();
		forward.setPath("./eventList.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}

}
