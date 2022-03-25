package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminProductListServise;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBoardDTO;

public class AdminProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminProductListAction");
		ActionForward forward = null;
		
		int pageNum = 1;
		
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		AdminProductListServise adminProductListServise = new AdminProductListServise();
		
		int listCount = adminProductListServise.getListCount();
		
		int listLimit = 10;
		int pageLimit = 10;
		
		ArrayList<ProductBoardDTO> list = adminProductListServise.getList(pageNum,listLimit);
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) -1 ) * pageLimit +1 ;
		int endPage = startPage + pageLimit -1 ;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list",list);
		
		forward = new ActionForward();
		forward.setPath("./management/product_management.jsp");
		
	
	return forward;
	}

}
