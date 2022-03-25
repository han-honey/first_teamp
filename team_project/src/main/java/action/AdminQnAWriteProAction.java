package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardNoticeWriteProService;
import svc.BoardQnAWriteProService;
import vo.ActionForward;
import vo.BoardDTO;
import vo.QnADTO;

public class AdminQnAWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminQnAWriteProAction");
		
		ActionForward forward = null;
		
		String uploadPath = "/upload";
		
		int fileSize = 1024 * 1024 * 10;
		
		ServletContext context = request.getServletContext();
		
		String realPath = context.getRealPath(uploadPath);
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath,
				fileSize,
				"UTF-8", 
				new DefaultFileRenamePolicy() 
			);
		
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("sesseionId");
		
		
		QnADTO qnaDTO = new QnADTO();
		qnaDTO.setMember_id(id);
		qnaDTO.setQuestion_category(multi.getParameter("QnACategory"));
		qnaDTO.setProduct_code(multi.getParameter("product_code"));
		qnaDTO.setQuestion_subject(multi.getParameter("subject"));
		qnaDTO.setQuestion_content(multi.getParameter("content"));
		System.out.println(id);
		
				
		String fileElement = multi.getFileNames().nextElement().toString();
		
		String file = multi.getFilesystemName(fileElement);

		System.out.println("실제 파일명 : " + file);
		
		qnaDTO.setQuestion_img(file);
		
		BoardQnAWriteProService questionAnswerWriteProService = new BoardQnAWriteProService();
		boolean isWriteSuccess = questionAnswerWriteProService.registArticle(qnaDTO);
		
		if(!isWriteSuccess) { 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('글 등록 실패!')"); 
			out.println("history.back()");
			out.println("</script>"); 
		} else { 
			forward = new ActionForward();
			forward.setPath("./AdminQnAList.ad");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
