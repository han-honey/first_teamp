<%@page import="javax.mail.Session"%>
<%@page import="vo.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<meta charset="UTF-8">
<script>
	function goParent() {
		window.close();
		opener.document.fr.email.readOnly = true;
		opener.document.fr.sendMailBtn.style.visibility = 'hidden';
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form action="./CheckEmail.me" method="post" name="fr" >
	<%
	String email = request.getParameter("email"); 
	if(email == null) {
		email = (String)session.getAttribute("certEmail");
	}
	request.setCharacterEncoding("UTF-8");
	String isCert = request.getParameter("isCert");
	
	if (isCert == null | isCert == "") {
	%>
			<div>
				<h4>가입한 이메일 주소로 발송된 인증번호를 </h4> 
				<h4>확인하여 입력창에 입력해주세요.</h4>
				인증번호 : <input type="text" name="cert" required="required">
				<input type="hidden" name="email" class="btn"  value="<%=email%>">
				<input type="submit" value="인증확인">
			</div>
	<%
		} else if (isCert.equals("true")) {
	%>
		<div>
		<h4>인증이 확인되었습니다.</h4>
		<h4>아래의 확인 버튼을 눌러 가입을 진행해주세요.</h4>
		<input type="button" value="확인" class="btn"  onclick="goParent()">
		</div>
		<%
		} else {
		%>
		<div>
				<h4>인증번호가 일치하지않습니다. </h4> 
				<h4>다시 입력해주세요.</h4>
				인증번호 : <input type="text" name="cert" required="required">
				<input type="hidden" name="email" class="btn"  value="<%=email%>">
				<input type="submit" value="인증확인">
			</div>
			<%} %>
		</form>
	</div>

</body>
</html>


