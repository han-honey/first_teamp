package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardQnAAnswerProService;
import vo.ActionForward;
import vo.QnADTO;

public class BoardQnAAnswerProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardQnAAnswerProAction");
		
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
		
		
		
		QnADTO board = new QnADTO();
		board.setQuestion_idx(Integer.parseInt(multi.getParameter("idx")));
		board.setMember_id(multi.getParameter("name"));
		board.setQuestion_password(multi.getParameter("pass"));
		board.setQuestion_subject(multi.getParameter("subject"));
		board.setQuestion_content(multi.getParameter("content"));
		
		String fileElement = multi.getFileNames().nextElement().toString();
		
		String file = multi.getFilesystemName(fileElement);
		
		board.setQuestion_img(file);
		
		BoardQnAAnswerProService boardQnAAnswerProService = new BoardQnAAnswerProService();
		
		boolean isModifySuccess = boardQnAAnswerProService.insertAnswer(board);
			
			if(!isModifySuccess) { 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>"); 
				out.println("alert('글 수정 실패!')");
				out.println("history.back()");
				out.println("</script>"); 
			} else { 
				forward = new ActionForward();
				forward.setPath("QnAContent.bo?idx=" + board.getQuestion_idx() + "&page=" + request.getParameter("page"));
				forward.setRedirect(true); 
			}
		
		return forward;
	}

}
