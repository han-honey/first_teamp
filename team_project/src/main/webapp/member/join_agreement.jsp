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
function checkAll() {
	if(document.fr.check_all.checked) {
		document.fr.agree[0].checked = true;
		document.fr.agree[1].checked = true;
	}
else {
		for(var i = 0; i < document.fr.agree.length; i++) {
			document.fr.agree[i].checked = false;
		}
}}

function check() {
	if(document.fr.agree[0].checked == false) { 
		alert("약관에 동의해주세요"); 
		return false; 
	} else if(document.fr.agree[1].checked == false) { 
		alert("개인정보 수집 및 이용에 동의해주세요."); 
		return false; 
	} else {
return true;
	}
}
</script>
</head>

<body>
	<jsp:include page="../inc/top.jsp" />
	<div align="center">
		<form action="./MemberJoinForm.me" method="post" name="fr" onsubmit="return check()">
			<div style="width: 600px;">
				<table style="text-align: left">
					<tr>
						<td>▣이용약관</td>
					</tr>
					<tr>
						<td><textarea rows="5" cols="80">
	제 1 조(목적)

본 약관은 푸릇푸릇 웹사이트(이하 "푸릇푸릇")가 제공하는 모든 서비스(이하 "서비스")의 이용조건 및 절차, 회원과 푸릇푸릇의 권리, 의무, 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다.

제 2 조(약관의 효력과 변경)

1. 푸릇푸릇은 이용자가 본 약관 내용에 동의하는 경우, 푸릇푸릇의 서비스 제공 행위 및 회원의 서비스 사용 행위에 본 약관이 우선적으로 적용됩니다.
2. 푸릇푸릇은 약관을 개정할 경우, 적용일자 및 개정사유를 명시하여 현행약관과 함께 푸릇푸릇의 초기화면에 그 적용일 7일 이전부터 적용 전일까지 공지합니다. 단, 회원에 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 푸릇푸릇은 개정 전 내용과 개정 후 내용을 명확하게 비교하여 회원이 알기 쉽도록 표시합니다.
3. 변경된 약관은 푸릇푸릇 홈페이지에 공지하거나 e-mail을 통해 회원에게 공지하며, 약관의 부칙에 명시된 날부터 그 효력이 발생됩니다. 회원이 변경된 약관에 동의하지 않는 경우, 회원은 본인의 회원등록을 취소(회원탈퇴)할 수 있으며, 변경된 약관의 효력 발생일로부터 7일 이내에 거부의사를 표시하지 아니하고 서비스를 계속 사용할 경우는 약관 변경에 대한 동의로 간주됩니다.

제 3 조(약관 외 준칙)

본 약관에 명시되지 않은 사항은 전기통신기본법, 전기통신사업법, 정보통신윤리위원회심의규정, 정보통신 윤리강령, 프로그램보호법 및 기타 관련 법령의 규정에 의합니다.

제 4 조(용어의 정의)

본 약관에서 사용하는 용어의 정의는 다음과 같습니다.
1. 이용자 : 본 약관에 따라 푸릇푸릇이 제공하는 서비스를 받는 자
2. 가입 : 푸릇푸릇이 제공하는 신청서 양식에 해당 정보를 기입하고, 본 약관에 동의하여 서비스 이용계약을 완료시키는 행위
3. 회원 : 푸릇푸릇에 개인 정보를 제공하여 회원 등록을 한 자로서 푸릇푸릇이 제공하는 서비스를 이용할 수 있는 자.
4. 계정(ID) : 회원의 식별과 회원의 서비스 이용을 위하여 회원이 선정하고 푸릇푸릇에서 부여하는 문자와 숫자의 조합
5. 비밀번호 : 회원과 계정이 일치하는지를 확인하고 통신상의 자신의 비밀보호를 위하여 회원 자신이 선정한 문자와 숫자의 조합
6. 탈퇴 : 회원이 이용계약을 종료시키는 행위
7. 본 약관에서 정의하지 않은 용어는 개별서비스에 대한 별도 약관 및 이용규정에서 정의합니다.
	
	</textarea></td>
					<tr>
						<td>위의 이용약관에 동의합니다. <input type="checkbox" name="agree"></td>
					</tr>
					<tr>
						<td>▣ 개인정보 수집 및 이용에 대한 안내</td>
					</tr>
					<tr>
						<td><textarea rows="5" cols="80">
가. 개인정보의 수집 및 이용 목적
① 푸릇푸릇은 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 개인정보 보호법 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.
1. 푸릇푸릇 서비스 제공을 위한 회원관리
1) 공간정보 다운로드, 오픈API 신청 및 활용 등 포털 서비스 제공과 서비스 부정이용 방지를 목적으로 개인정보를
   처리합니다.

나. 정보주체와 법정대리인의 권리ㆍ의무 및 행사방법
① 정보주체(만 14세 미만인 경우에는 법정대리인을 말함)는 언제든지 개인정보 열람·정정·삭제·처리정지 요구 등의 권리를 행사할 수 있습니다.
② 제1항에 따른 권리 행사는 개인정보보호법 시행규칙 별지 제8호 서식에 따라 작성 후 서면, 전자우편 등을 통하여 하실 수 있으며, 기관은 이에 대해 지체 없이 조치하겠습니다.
③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 개인정보 보호법 시행규칙 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.
④ 개인정보 열람 및 처리정지 요구는 개인정보 보호법 제35조 제5항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.
⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.
⑥ 정보주체 권리에 따른 열람의 요구, 정정ㆍ삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.

다. 수집하는 개인정보의 항목
① 푸릇푸릇 회원정보(필수): 이름, 이메일(아이디), 비밀번호

라. 개인정보의 보유 및 이용기간
① 푸릇푸릇은 법령에 따른 개인정보 보유ㆍ이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의 받은 개인정보 보유ㆍ이용기간 내에서 개인정보를 처리ㆍ보유합니다.
1. 푸릇푸릇 회원정보
- 수집근거: 정보주체의 동의
- 보존기간: 회원 탈퇴 요청 전까지(1년 경과 시 재동의)
- 보존근거: 정보주체의 동의

마. 동의 거부 권리 및 동의 거부에 따른 불이익
위 개인정보의 수집 및 이용에 대한 동의를 거부할 수 있으나, 동의를 거부할 경우 회원 가입이 제한됩니다.
	
	</textarea></td>
					</tr>
					<tr>
						<td>위의 개인정보 수집 및 이용에 대한 안내에 동의합니다. 
						<input type="checkbox" name="agree"></td>
					</tr>
					<tr align="center">
						<td>이용약관, 개인정보 수집 및 이용에 모두 동의합니다. 
						<input type="checkbox" name="check_all" onclick="checkAll()"></td>
					</tr>
					<tr align="center">
						<td><input type="submit" value="확인"> 
						<input type="button" value="취소" onclick="history.back()"></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<jsp:include page="../inc/bottom.jsp" />
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


