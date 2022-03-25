<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="../css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="../css/nice-select.css" type="text/css">
<link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="../css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<script>
function check() {
	if(document.fr.agree.checked == false) { 
		alert("탈퇴안내를 확인하고 동의해주세요"); 
		return false; 
	}  else {
		return true;
	}
}
</script>
</head>

<body>
	<jsp:include page="../inc/top.jsp" />
	<div align="center">
		<form action="./MemberDeletePro.me" method="post" name="fr" onsubmit="return check()">
				<h2>탈퇴 안내</h2>
			<div style="width: 600px; text-align: left; border: thin;">
			<div>
			<h4>사용하고 계신 아이디(<em>${sessionScope.sessionId }</em>)는 탈퇴할 경우 재사용 및 복구가 불가능합니다.</h4>
			<p>
			<em>탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가</em>하오니 신중하게 선택하시기 바랍니다.
			</p>
			<h4>탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.</h4>
			<p>
						     	  회원정보 및 메일, 블로그, 주소록 등 개인형 서비스 이용기록은 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.<br>삭제되는 내용을 확인하시고 필요한 데이터는 미리 백업을 해주세요.
						     </p>
			<h4>탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.</h4>
           </div> 
			<h4>탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아 있습니다.</h4>
			<p>뉴스, 카페, 지식iN 등에 올린 게시글 및 댓글은 탈퇴 시 자동 삭제되지 않고 그대로 남아 있습니다.
			<br>삭제를 원하는 게시글이 있다면 <em>반드시 탈퇴 전 비공개 처리하거나 삭제하시기 바랍니다.</em>
			<br>탈퇴 후에는 회원정보가 삭제되어 본인 여부를 확인할 수 있는 방법이 없어, 게시글을 임의로 삭제해드릴 수 없습니다. <br>
			</p>
			<h4>탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아 있습니다.</h4>
					<p class="contxt">
						<strong>
						     	  탈퇴 후에는 아이디 <em>${sessionScope.sessionId }</em> 로 다시 가입할 수 없으며 아이디와 데이터는 복구할 수 없습니다.
						     <br>
								 게시판형 서비스에 남아 있는 게시글은 탈퇴 후 삭제할 수 없습니다.<br>또한, 네이버 아이디를 사용해 다른 서비스에 로그인 할 수 없게 됩니다. 
							</strong>
					</p>
					<input type="checkbox" name="agree" onclick="clickcr(this,'otn.guideagree','','',event);">
					<label for="dropoutAgree"><strong>안내 사항을 모두 확인하였으며, 이에 동의합니다.</strong></label>
				</div>
				<input type="submit" value="확인" class="btn" >
			</form>
		</div>
	<jsp:include page="../inc/bottom.jsp" />
		
	   <!-- Js Plugins -->
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


