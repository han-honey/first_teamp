package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardProductListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBoardDTO;

public class BoardProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardProductListAction - execute() 메서드 호출");
		ActionForward forward = null;
		
		String searchProductName = request.getParameter("searchProductName"); // 검색어 가져오기
		
		int pageNum = 1;
		
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		int listLimit = 5;
		int pageLimit = 5;
		
		int listCount = 0;
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) -1 ) * pageLimit +1 ;
		int endPage = startPage + pageLimit -1 ;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		
		String product_category1 = "ALL";
		if(request.getParameter("product_category1") != null) {
			product_category1 = request.getParameter("product_category1"); // 카테고리 접근 시 카테고리 가져오기
		}
//		System.out.println(product_category1);
		
		BoardProductListService boardProductListService = new BoardProductListService();
		ArrayList<ProductBoardDTO> list = null;
		
		if (searchProductName != null) { // 검색해서 리스트 접근할 경우(카테고리 고려X)
			listCount = boardProductListService.getSearchListCount(searchProductName);
			list = boardProductListService.getList(searchProductName,pageNum,listLimit);
		
			
		} else { // 검색X 전체 리스트 접근할 경우(카테고리 접근 포함)
			listCount = boardProductListService.getAllListCount(product_category1);
			list = boardProductListService.getList(pageNum,listLimit,product_category1);
			
		}
		
	
		System.out.println("BoardProductListAction - execute() : svc에서 넘겨받은 pageInfo : " + pageInfo);
		System.out.println("BoardProductListAction - execute() : svc에서 넘겨받은 list : " + list);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list",list);
		
		forward = new ActionForward();
		forward.setPath("../product/productList.jsp");
		
	
	return forward;
	}

}
