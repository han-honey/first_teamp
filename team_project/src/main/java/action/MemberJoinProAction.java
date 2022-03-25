package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinProService;
import vo.ActionForward;
import vo.Earning_pointDTO;
import vo.MemberDTO;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinProAction");
		ActionForward forward = null;
		response.setCharacterEncoding("UTF-8");
		// 1. MemberDTO 객체 생성 및 폼파라미터 데이터 저장
		MemberDTO member = new MemberDTO();
		member.setMember_id(request.getParameter("id")); 
		member.setMember_password(request.getParameter("pass"));
		member.setMember_name(request.getParameter("name"));
		member.setMember_email(request.getParameter("email"));
		member.setMember_phone(request.getParameter("phone"));
		member.setMember_birth(request.getParameter("birth"));
		member.setMember_marketing_agree(request.getParameter("marketing_agree"));
		
		MemberJoinProService service = new MemberJoinProService();
		
		boolean isInsertInfo = service.insertMemberInfo(member);
		
		if(!isInsertInfo) { // 실패 시(isWriteSuccess == false)
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('회원가입 실패!')"); // 메세지는 작은따옴표('')로 표기해야함
			out.println("history.back()");
			out.println("</script>"); // 자바스크립트 끝 태그
		} else { // 성공 시
			Earning_pointDTO pointDTO = new Earning_pointDTO();
			pointDTO.setMember_id(request.getParameter("id"));
			pointDTO.setEarning_history("회원가입 축하 적립금");
			pointDTO.setEarning_change(1000);
			
			boolean isPointChange = service.modifyPoint(pointDTO);
			if(isPointChange) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('회원가입 성공! 로그인 해주세요.')"); // 메세지는 작은따옴표('')로 표기해야함
			out.println("location.href='./MemberLoginForm.me'");
			out.println("</script>"); // 자바스크립트 끝 태그
			}
		}
		return forward;
	}
	}


