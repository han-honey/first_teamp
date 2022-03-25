package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardEventWriteProService;
import vo.ActionForward;
import vo.BoardEventDTO;

public class AdminEventWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminEventWriteProAction");
		
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
		
		BoardEventDTO eventBoard = new BoardEventDTO();
		eventBoard.setEvent_name(multi.getParameter("name"));
		eventBoard.setEvent_subject(multi.getParameter("subject"));
		eventBoard.setEvent_content(multi.getParameter("content"));
				
		String fileElement = multi.getFileNames().nextElement().toString();
		
		String file = multi.getFilesystemName(fileElement);

		System.out.println("실제 파일명 : " + file);
		
		eventBoard.setEvent_img(file);
		
		BoardEventWriteProService eventBoardWriteProService = new BoardEventWriteProService();
		boolean isWriteSuccess = eventBoardWriteProService.registArticle(eventBoard);
		
		if(!isWriteSuccess) { 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('글 등록 실패!')"); 
			out.println("history.back()");
			out.println("</script>"); 
		} else { 
			forward = new ActionForward();
			forward.setPath("./AdminEventList.ad");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
