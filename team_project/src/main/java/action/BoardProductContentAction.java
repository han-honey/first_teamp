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

public class BoardProductContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardProductContentAction - execute() 메서드 호출");
		
		ActionForward forward = null;
		
		String product_code = request.getParameter("product_code");
		System.out.println("product_code = " + product_code);
		
		BoardProductContentService boardProductContentService = new BoardProductContentService();
		
		ReviewListService reviewListService = new ReviewListService();
		
		ProductBoardDTO dto = boardProductContentService.getArticle(product_code);
		request.setAttribute("dto", dto);
		// -------------------------------- 페이징 처리를 위한 코드 -----------------------------------------
		int pageNum = 1;
		
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		int listLimit = 5;
		int pageLimit = 5;
		int listCount = 0;
		
		listCount = reviewListService.getListCount(product_code);
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) -1 ) * pageLimit +1 ;
		int endPage = startPage + pageLimit -1 ;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// -------------------------------- 페이징 처리를 위한 코드 -----------------------------------------
		ArrayList<ReviewDTO> articleList = reviewListService.getArticleList(pageNum,listLimit,product_code);
		request.setAttribute("articleList", articleList);
		
		int reviewCount = reviewListService.reviewCount(product_code);	
		request.setAttribute("reviewCount", reviewCount);
		
		double starScope = reviewListService.starScope(product_code);
		request.setAttribute("starScope", starScope);
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount); 
		request.setAttribute("pageInfo", pageInfo);
		
		forward = new ActionForward();
		forward.setPath("./productDetails.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}














