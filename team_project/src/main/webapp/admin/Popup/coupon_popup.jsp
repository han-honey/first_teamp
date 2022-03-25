<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 부트스트랩 사용하기 위해 -->

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>쿠폰생성 페이지</title>

</head>

<body>
	<form method="post" action="contentWriteProcess.jsp">
		<div class="container">
			<h2>쿠폰생성</h2>
			<table class="table table-hover">
				<tbody>
					<tr>
						<td colspan="2"><input type="text" class="form-control"
							placeholder="쿠폰명" name="contentTitle" maxlength="40"></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control" style="background: white;"
							placeholder="쿠폰코드" name="contentTitle" readonly="readonly">
						</td>
						<td><button class="btn btn-primary pull-right">난수생성</button></td>
							
					</tr>
					<tr>
						<td colspan="2"><input type="text" class="form-control"
							placeholder="할인정보" name="contentTitle" maxlength="40"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" class="form-control"
							placeholder="발급일" name="contentTitle" maxlength="40"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" class="form-control"
							placeholder="만료일" name="contentTitle" maxlength="40"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" class="form-control"
							placeholder="유효기간" name="contentTitle" maxlength="40"></td>
					</tr>			
				</tbody>
			</table>
			<input type="submit" class="btn btn-primary pull-right" value="쿠폰생성">
		</div>
	</form>
</body>

</html>