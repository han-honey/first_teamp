<%@page import="vo.AddressDTO"%>
<%@page import="dao.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%
	AddressDTO addressDTO = (AddressDTO)request.getAttribute("addressDTO");
	
	%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<title>배송지 등록창</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {            	
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open({
        	popupKey: 'popup1'
        });
    }
    
    function submitForm(){
    	document.getElementById("fr").submit();
    	
    }
    
    function closeForm() {
    	window.close();
    }
    
    function showValue() {
    	if(document.getElementById("default").checked){
    		document.getElementById("default").value = 1;
    	} else {
    		document.getElementById("default").value = 0;
    	}
    }
   
</script>
</head>
<body>
	<div align="center">
			<h4>배송지 정보 입력</h4>
			<form action="./MemberAddressAddPro.me" method="post" id="fr" >
			<table>
		  		<tr>
					<th>우편번호</th>
					<td>
				<input type="hidden" name="address_idx" value="${addressDTO.getAddress_idx() }" >
						<input type="text" id="sample6_postcode"  name="address_post" placeholder="우편번호" readonly="readonly" required="required"
						value="${addressDTO.getAddress_post() }">
					</td>
				</tr>
				<tr>
				<th>주소</th>
				<td><input type="text" id="sample6_address" name="address" placeholder="주소" readonly="readonly" required="required"
				value="${addressDTO.getAddress() }"></td>
				</tr>
				<tr>
				<th>상세주소</th>
				<td><input type="text" id="sample6_detailAddress" name="extra_address" placeholder="상세주소" value="${addressDTO.getExtra_address() }"></td>
				</tr>
				<tr>
				<th>참고항목</th>
				<td><input type="text" id="sample6_extraAddress" placeholder="참고항목" readonly="readonly" ></td>
				</tr>
				<tr>
				<th>요청메세지</th>
				<td><input type="text" name="request_message" placeholder="요청메세지" value="${addressDTO.getRequest_message() }"></td>
				</tr>
				<tr>
				<th>기본배송지</th>
				<c:choose>
				<c:when test="${addressDTO.getAddress_default() eq 1 }">
				<td><input type="checkbox" name="address_default" id="default" onclick="showValue()" checked="checked" >기본 배송지로 설정</td>
				</c:when>
				<c:otherwise>
				<td><input type="checkbox" name="address_default" id="default" onclick="showValue()">기본 배송지로 설정</td>
				</c:otherwise>
				</c:choose>
				</tr>
		</table>
		</form>
		  		<div class="clear"></div>
		  		<div id="buttons">
		  			<input type="button" value="배송지 조회" onclick="sample6_execDaumPostcode()">
		  		</div>		  
				<div class="button_section">
     			   <span><a href="#" onclick="submitForm()">저장</a></span>
       				 <span>|</span>
       				 <span><a href="#"  onclick="closeForm()">닫기</a></span>
   				 </div>
		</div>

</body>
</html>


