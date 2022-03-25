<%@page import="vo.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	function goParent() {
		opener.document.fr.id.value = document.fr2.id2.value;
		window.close();
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<form action="./IdCheckPro.me" method="post" name="fr2">
		<%
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id2");

		String duplicate_id = request.getParameter("duplicate_id");
		System.out.println(duplicate_id);
		if (id == null | id == "") {
		%>
		<input type="text" name="id2" id="id2" placeholder="아이디 입력">
		<input type="submit" value="확인">
		<%
		} else if (id != null && !duplicate_id.equals("duplicate")) {
		%>
		<input type="text" name="id2" id="id2" value="<%=duplicate_id%>" readonly="readonly"> 
			<input type="button" value="아이디 사용하기" onclick="goParent()">
		<%
		out.println("<br>사용가능한 아이디입니다.");
		} else if (duplicate_id.equals("duplicate")) {
		%>
		<input type="text" name="id2" id="id2"
			placeholder="다른 아이디 입력"> <input type="submit" value="확인">
		<%
		out.println("<br>이미 사용중인 아이디입니다.");
		}
		%>
	</form>
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.nice-select.min.js"></script>
    <script src="../js/jquery-ui.min.js"></script>
    <script src="../js/jquery.slicknav.js"></script>
    <script src="../js/mixitup.min.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/main.js"></script>
    <script src="../js/jquery-3.6.0.js"></script>
</body>
</html>