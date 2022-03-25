<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.PaymentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
PaymentDTO orderResult = (PaymentDTO)request.getAttribute("orderResult");
String strDate = orderResult.getOrder_date() + "";
String[] frontOrderNum = strDate.split(" ")[0].split("-");
System.out.println(Arrays.toString(frontOrderNum));
int order_idx = orderResult.getOrder_idx();
String backOrderNum = String.format("%08d", order_idx);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>고객님의 주문이 완료 되었습니다.</h1>
	<h6>주문내역 및 배송에 관한 안내는 <a href="OrderList.pa">주문조회</a>를 통하여 확인 가능합니다</h6>
	<form action="../main/Main.ma">
		<table>
			<tr>
				<th>
					주문번호 :
				</th>
				<td>
					<%=frontOrderNum[0]+frontOrderNum[1]+frontOrderNum[2]%>-<%=backOrderNum%>
				</td>
			</tr>
			<tr>
				<th>
					주문일자 :
				</th>
				<td>
					<%=strDate %>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="홈으로가기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>