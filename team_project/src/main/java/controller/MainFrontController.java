package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardProductListAction;
import action.MainRankingListAction;
import vo.ActionForward;

@WebServlet("*.ma")
public class MainFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MainFrontController");
		
		request.setCharacterEncoding("UTF-8");
		
		Action action = null;
		
		ActionForward forward = null;
		
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		if(command.equals("/main/Main.ma")) {
			
//			forward = new ActionForward();
//			// 2. ActionForward 객체를 통해 포워딩 주소를 저장 
//			forward.setPath("./main.jsp");
//			// 3. ActionForward 객체를 통해 포워딩 방식 지정
//			// => 뷰페이지로 이동할 경우에는 Dispatcher 방식 사용(URL 정보 숨김)
//			forward.setRedirect(false); // false 값일 경우 메서드 호출 생략 가능
			
			action = new MainRankingListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if (forward != null) {
			if (forward.isRedirect()) { // Redirect 방식일 경우
				// Redirect 방식으로 포워딩 => response 객체의 sendRedirect() 메서드 호출
				response.sendRedirect(forward.getPath());
			} else { // Dispatcher 방식일 경우
				// RequestDispatcher 객체의 forward() 메서드 호출
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
