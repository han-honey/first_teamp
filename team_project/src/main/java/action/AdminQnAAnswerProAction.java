package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardQnAAnswerProService;
import vo.ActionForward;
import vo.QnADTO;

public class AdminQnAAnswerProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminQnAAnswerProAction");
		
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
		
		int pageNum = Integer.parseInt(request.getParameter("page"));
	
		
		QnADTO board = new QnADTO();
		board.setQuestion_idx(Integer.parseInt(multi.getParameter("idx")));
		board.setMember_id(multi.getParameter("name"));
		board.setQuestion_subject(multi.getParameter("subject"));
		board.setQuestion_content(multi.getParameter("content"));
		board.setQuestion_img(multi.getParameter("file"));
		
		String fileElement = multi.getFileNames().nextElement().toString();
		
		String file = multi.getFilesystemName(fileElement);
		
		board.setQuestion_img(file);
		
		BoardQnAAnswerProService boardQnAAnswerProService = new BoardQnAAnswerProService();
		
		boolean isInsertSuccess = boardQnAAnswerProService.insertAnswer(board);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(!isInsertSuccess) { 
			out.println("<script>"); 
			out.println("alert('글 수정 실패!')");
			out.println("history.back()");
			out.println("</script>"); 
		} else { 
			forward = new ActionForward();
			forward.setPath("../center/QnAList.bo?idx=" + board.getQuestion_idx() + "&page=" + pageNum + "");
			forward.setRedirect(true); 
		} 
		
		
		return forward;
	}

}
