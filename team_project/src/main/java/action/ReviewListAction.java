package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardProductContentService;
import svc.ReviewListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBoardDTO;
import vo.ReviewDTO;

public class ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewListAction");
		
		ActionForward forward = null;
		
		String product_code = request.getParameter("product_code");
		
		int pageNum = 1;
		
		if(request.getParameter("page") != null){
			pageNum = Integer.parseInt(request.getParameter("page"));
			System.out.println(pageNum);
			
		}
	
		ReviewListService reviewListService = new ReviewListService();
	   
	    int listCount = reviewListService.getListCount(product_code);
	   
	    int listLimit = 5;
	    int pageLimit = 5;
	   
	    int maxPage = (int)Math.ceil((double)listCount / listLimit);	
	    int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
	    int endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		BoardProductContentService boardProductContentService = new BoardProductContentService();
		ProductBoardDTO dto = boardProductContentService.getArticle(product_code);
		request.setAttribute("dto", dto);
		
		ArrayList<ReviewDTO> articleList = reviewListService.getArticleList(pageNum,listLimit,product_code);
		request.setAttribute("articleList", articleList);
		
		int reviewCount = reviewListService.reviewCount(product_code);	
		request.setAttribute("reviewCount", reviewCount);
		
		double starScope = reviewListService.starScope(product_code);
		request.setAttribute("starScope", starScope);
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		request.setAttribute("pageInfo", pageInfo);
		
		forward = new ActionForward();
		forward.setPath("./review_list.jsp?page="+pageNum+"&product_code="+product_code);
		forward.setRedirect(false);
	
	
	
	return forward;
	
	}

}
