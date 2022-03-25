package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MainRankingListServise;
import vo.ActionForward;
import vo.ProductBoardDTO;

public class MainRankingListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MainRankingListAction - execute() 메서드 호출");
		ActionForward forward = null;
		
		MainRankingListServise mainRankingListServise = new MainRankingListServise();
		
		ArrayList<ProductBoardDTO> newRankingList = mainRankingListServise.newRankingList();
		ArrayList<ProductBoardDTO> sellRankingList = mainRankingListServise.sellRankingList();
		ArrayList<ProductBoardDTO> starScoreRankingList = mainRankingListServise.starScoreRankingList();
		
		System.out.println("MainRankingListAction - execute() : svc에서 넘겨받은 newRankingList : " + newRankingList);
		System.out.println("MainRankingListAction - execute() : svc에서 넘겨받은 sellRankingList : " + sellRankingList);
		
		request.setAttribute("newRankingList", newRankingList);
		request.setAttribute("sellRankingList", sellRankingList);
		request.setAttribute("starScoreRankingList", starScoreRankingList);
		
		forward = new ActionForward();
		forward.setPath("../main/main.jsp");
		
		return forward;
	}

}
