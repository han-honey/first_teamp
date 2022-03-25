package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.PaymentCartAllDeleteAction;
import action.PaymentCartDeleteAction;
import action.PaymentCartInsertAction;
import action.PaymentCartListAction;
import action.PaymentCartUpdateAction;
import action.PaymentCheckoutWriteFormAction;
import action.PaymentCheckoutWriteProAction;
import action.PaymentOrderContentAction;
import action.PaymentOrderListAction;
import action.PaymentOrderResultAction;
import action.PaymentWishlistAction;
import action.PaymentWishlistAddAction;
import action.PaymentWishlistDeleteAction;
import vo.ActionForward;

@WebServlet("*.pa")
public class PaymentFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PaymentFrontController");
		
		request.setCharacterEncoding("UTF-8");
		
		Action action = null;
		
		ActionForward forward = null;
		
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// ------------- Checkout --------------
		
		if(command.equals("/payment/CheckoutForm.pa")) { // 결제창 이동
			
			action = new PaymentCheckoutWriteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} else if(command.equals("/payment/CheckoutPro.pa")) { // 결제처리
			
			action = new PaymentCheckoutWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/payment/OrderList.pa")) { // 결제리스트
			
			action = new PaymentOrderListAction(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/payment/OrderResult.pa")) { // 결제 후 결과창
			
			action = new PaymentOrderResultAction(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/payment/OrderContent.pa")) { 
			
			action = new PaymentOrderContentAction(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		// ------------------ Wishlist -----------------------
		
		if(command.equals("/payment/Wishlist.pa")) { 
			System.out.println("PaymentFrontController - Wishlist.pa 주소 요청됨");
			action = new PaymentWishlistAction(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/payment/WishlistAdd.pa")) {
			System.out.println("PaymentFrontController - WishlistAdd.pa 주소 요청됨");
			action = new PaymentWishlistAddAction(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
						
		} 
		else if(command.equals("/payment/WishlistDelete.pa")) {
			System.out.println("PaymentFrontController - WishlistDelete.pa 주소 요청됨");
				
			action = new PaymentWishlistDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		
		// ------------------ cart -----------------------
		
		if(command.equals("/payment/CartList.pa")) {
			System.out.println("CartFrontController - CartList.pa 주소 요청됨");
			
			action = new PaymentCartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/payment/CartInsert.pa")) {
			System.out.println("CartFrontController - CartInsert.pa 주소 요청됨");
			
			action = new PaymentCartInsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/payment/CartUpdate.pa")) {
			System.out.println("CartFrontController - CartUpdate.pa 주소 요청됨");
			
			action = new PaymentCartUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/payment/CartDelete.pa")) {
			System.out.println("CartFrontController - CartDelete.pa 주소 요청됨");
			
			action = new PaymentCartDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/payment/CartAllDelete.pa")) {
			System.out.println("CartFrontController - CartAllDelete.pa 주소 요청됨");
			
			action = new PaymentCartAllDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		// ------------------ addressAPI -----------------------
		
		if(command.equals("/payment/AddressAPI.pa")) {
			
			forward = new ActionForward();
			forward.setPath("./jusoPopup.jsp");
			forward.setRedirect(false);
			
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
		doProcess(request, response);
	}

}
