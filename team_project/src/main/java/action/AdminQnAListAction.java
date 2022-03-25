package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardQnAListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.QnADTO;

public class AdminQnAListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("AdminQnAListAction");
		
		ActionForward forward = null;
		
		int pageNum = 1; 
		
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		BoardQnAListService questionAnswerListService = new BoardQnAListService();
		
		int listCount = questionAnswerListService.getQnAListCount(); 
		
		int listLimit = 10;
		int pageLimit = 10; 
		
		ArrayList<QnADTO> articleList = 
				questionAnswerListService.getQnAArticleList(pageNum, listLimit);
		
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
		forward.setPath("./QnA.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}	

}
