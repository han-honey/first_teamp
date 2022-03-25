package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminChartListService;
import vo.ActionForward;
import vo.PaymentDTO;
import vo.ProductBoardDTO;

public class AdminChartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("AdminChartAction");
		
		AdminChartListService adminChartListService = new AdminChartListService();
		
		ArrayList<ProductBoardDTO> list = adminChartListService.getList();
		request.setAttribute("chartList", list);
		
		forward = new ActionForward();
		forward.setPath("./management/sales_management.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
