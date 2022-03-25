<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>review_delete.jsp</title>
</head>
<body>
	<h1>리뷰 삭제</h1>
		<form action="BoardDriverDeletePro.bo" method="post">
				<input type="hidden" name="review_idx" value="<%=request.getParameter("review_idx")%>">
				<table>
					<tr>
						<td>패스워드</td>
						<td><input type="password" name="pass" ></td>
					</tr>
				</table>

				<div>
					<input type="submit" value="글삭제" class="btn">
				</div>
			</form>

</body>
</html>


