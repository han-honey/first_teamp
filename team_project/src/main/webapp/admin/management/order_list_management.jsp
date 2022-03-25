<%@page import="java.util.Date"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.PaymentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<PaymentDTO> orderList = (ArrayList<PaymentDTO>) request.getAttribute("paymentDTO");
String[] frontOrderNum = new String[orderList.size()];
String[] backOrderNum = new String[orderList.size()];

for(int i=0; i<orderList.size(); i++){
	String strDate = orderList.get(i).getOrder_date()+"";
	frontOrderNum[i] = strDate.split(" ")[0].replace("-", ""); //2022-01-01 21:59:59
	System.out.println(frontOrderNum[i]);
	
	int order_idx = orderList.get(i).getOrder_idx();
	backOrderNum[i] = (String)(String.format("%08d", order_idx)); 
	
}
PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
int pageNum = pageInfo.getPageNum();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
int listCount = pageInfo.getListCount();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>주문관리</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
		<jsp:include page="../inc/adminTop.jsp"/>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">주문관리</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-shopping-cart"></i>
                                주문관리
                            </div>
                       
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>주문번호</th>
                                            <th>주문자ID</th>
											<th>주문상품명</th>
											<th>주문개수</th>
											<th>상품금액</th>
											<th>결제금액</th>
											<th>결제날짜</th>
											<th>배송요청사항</th>   
                                        </tr>
                                   </thead>
                                    <tbody>
                                    	<%
						 				if (orderList != null && orderList.size() > 0) {
						 					for (int i=0; i<orderList.size(); i++) {
										%>
<%-- 										 onclick="location.href='../payment/OrderContent.pa?idx=<%=orderList.get(i).getOrder_idx()%>&page=<%=pageNum%>'" --%>
                                        <tr>
                                            <td><%=frontOrderNum[i]%>-<%=backOrderNum[i]%></td>
                                            <td><%=orderList.get(i).getOrderer_id()%></td>
											<td><%=orderList.get(i).getProuct_name()%></td>
											<td><%=orderList.get(i).getOrder_product_amount()[0]%></td>
											<td><%=orderList.get(i).getProduct_price()%></td>
											<td><%=orderList.get(i).getOrder_product_amount()[0]*orderList.get(i).getProduct_price()%></td>
											<td><%=orderList.get(i).getOrder_date()%></td>
	                                        <%if(orderList.get(i).getOrder_request_message()==null) {%>
												<td> </td>
											<%} else { %>
												<td><%=orderList.get(i).getOrder_request_message()%></td>
											<%} %>
										</tr>
										<%
						 				}
						 				} else {
										%>
										<!--주문내역이 없을 경우 -->
										<tr>
											<td colspan="5">주문내역이 없습니다.</td>
										</tr>
										<%}%> 
                                    </tbody>
                                    
                                </table>
                               
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
