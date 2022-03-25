package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardEventModifyProService;
import vo.ActionForward;
import vo.BoardEventDTO;

public class AdminEventModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminEventModifyProAction");
		
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
		
		
		
		BoardEventDTO board = new BoardEventDTO();
		board.setEvent_idx(Integer.parseInt(multi.getParameter("idx")));
		board.setEvent_name(multi.getParameter("name"));
		board.setEvent_subject(multi.getParameter("subject"));
		board.setEvent_content(multi.getParameter("content"));
		
		String fileElement = multi.getFileNames().nextElement().toString();
		
		String file = multi.getFilesystemName(fileElement);
		
		board.setEvent_img(file);
		
		BoardEventModifyProService boardEventModifyProService = new BoardEventModifyProService();
		
		boolean isModifySuccess = boardEventModifyProService.modifyArticle(board);
			
			if(!isModifySuccess) { 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>"); 
				out.println("alert('글 수정 실패!')");
				out.println("history.back()");
				out.println("</script>"); 
			} else { 
				forward = new ActionForward();
				forward.setPath("AdminEventContent.ad?idx=" + board.getEvent_img() + "&page=" + request.getParameter("page"));
				forward.setRedirect(true); 
			}
		
		return forward;
	}

}
