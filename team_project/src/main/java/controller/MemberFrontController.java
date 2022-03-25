package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MemberAddressAddProAction;
import action.MemberAddressDeleteAction;
import action.MemberAddressListAction;
import action.MemberAddressUpdateAction;
import action.MemberChangePasswordAction;
import action.MemberCheckPasswordAction;
import action.MemberDeleteProAction;
import action.MemberEmailCheckAction;
import action.MemberEmailSendAction;
import action.MemberIdCheckProAction;
import action.MemberInfoAction;
import action.MemberInfoUpdateAction;
import action.MemberJoinProAction;
import action.MemberLoginProAction;
import action.MemberSearchIdAction;
import action.MemberSearchPwAction;
import vo.ActionForward;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController");
		
		// POST 방식 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		Action action = null;
		ActionForward forward = null;
		if(command.equals("/member/MemberJoinForm.me")) {
			forward = new ActionForward();
			forward.setPath("./join.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/member/MemberAgreeForm.me")) {
			forward = new ActionForward();
			forward.setPath("./join_agreement.jsp");
			forward.setRedirect(false);
		} 
		else if(command.equals("/member/IdCheckForm.me")) {
			forward = new ActionForward();
			forward.setPath("./idCheck.jsp");
			forward.setRedirect(false);
		} 
		else if(command.equals("/member/IdCheckPro.me")) {
			action = new MemberIdCheckProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/member/MemberJoinPro.me")) {
			action = new MemberJoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/member/SendEmail.me")) {
			action = new MemberEmailSendAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/member/CheckEmail.me")) {
			action = new MemberEmailCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//---------- 로그인 -------------
		else if(command.equals("/member/MemberLoginForm.me")) {
			forward = new ActionForward();
			forward.setPath("./login.jsp");
			forward.setRedirect(true);
		} 
		else if(command.equals("/member/MemberLoginPro.me")) {
			action = new MemberLoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/member/MemberLogout.me")) {
			forward = new ActionForward();
			forward.setPath("./logout.jsp");
			forward.setRedirect(true);
		} 
		//---------회원정보 --------------
		else if(command.equals("/member/MemberInfo.me")) {
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/member/CheckPassword.me")) {
			forward = new ActionForward();
			forward.setPath("./checkPassword.jsp");
			forward.setRedirect(true);
		} 
		else if(command.equals("/member/CheckPasswordPro.me")) {
			action = new MemberCheckPasswordAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/member/UpdateMemberInfo.me")) {
			action = new MemberInfoUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/member/ChangePasswordForm.me")) {
			forward = new ActionForward();
			forward.setPath("./changePassword.jsp");
			forward.setRedirect(true);
		} 
		else if(command.equals("/member/ChangePasswordPro.me")) {
			action = new MemberChangePasswordAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
//		------------- 아이디/패스워드 찾기 -------------------
		else if(command.equals("/member/SearchIdForm.me")) {
			forward = new ActionForward();
			forward.setPath("./searchId.jsp");
			forward.setRedirect(true);
		} 
		else if(command.equals("/member/SearchIdPro.me")) {
			action = new MemberSearchIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/member/SearchPasswordForm.me")) {
			forward = new ActionForward();
			forward.setPath("./searchPw.jsp");
			forward.setRedirect(true);
		} 
		else if(command.equals("/member/SearchPasswordPro.me")) {
			action = new MemberSearchPwAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
//		--------------------------------------------------------------
		else if(command.equals("/member/MemberDeleteForm.me")) { //회원탈퇴 
			forward = new ActionForward();
			forward.setPath("./memberDelete.jsp");
			forward.setRedirect(true);
		} 
		else if(command.equals("/member/MemberDeletePro.me")) { 
			action = new MemberDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
//		--------------------------------------------------------------
		else if(command.equals("/member/MemberAddressList.me")) { //배송지 목록 조회
			action = new MemberAddressListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/member/MemberAddressAddForm.me")) { 
			forward = new ActionForward();
			forward.setPath("./addressInsert.jsp");
			forward.setRedirect(true);
		} 
		else if(command.equals("/member/MemberAddressAddPro.me")) { //배송지 추가
			action = new MemberAddressAddProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/member/MemberAddressDelete.me")) { //배송지 삭제
			action = new MemberAddressDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/member/MemberAddressUpdateForm.me")) { //배송지 수정
			action = new MemberAddressUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
//		------------------------------------------------
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
