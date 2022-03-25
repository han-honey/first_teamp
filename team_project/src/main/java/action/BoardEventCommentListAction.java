package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardQnAListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.QnADTO;

public class BoardEventCommentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("BoardEventCommentListAction");
		
		ActionForward forward = null;
		
		int pageNum = 1; 
		System.out.println(request.getParameter("page"));
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		BoardQnAListService questionAnswerListService = new BoardQnAListService();
		
		int articleListCount = questionAnswerListService.getQnAListCount(); 
		
		
		int listLimit = 10;
		int pageLimit = 10;
		
		ArrayList<QnADTO> articleList = 
				questionAnswerListService.getQnAArticleList(pageNum, listLimit);
		
		int maxPage = (int)Math.ceil((double)articleListCount / listLimit);	
		
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		
		int endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, articleListCount);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		forward = new ActionForward();
		forward.setPath("./QnAList.jsp?page=" + pageNum);
		forward.setRedirect(false);
		
		return forward;
	}	

}
