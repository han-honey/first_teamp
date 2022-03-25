package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardNoticeWriteProService;
import vo.ActionForward;
import vo.BoardDTO;


public class AdminNoticeWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminNoticeWriteProAction");
		
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
		
		BoardDTO board = new BoardDTO();
		board.setName(multi.getParameter("name"));
		board.setSubject(multi.getParameter("subject"));
		board.setContent(multi.getParameter("content"));
		board.setImg(multi.getParameter("img"));
				
		String fileElement = multi.getFileNames().nextElement().toString();
		
		String file = multi.getFilesystemName(fileElement);

		System.out.println("실제 파일명 : " + file);
		
		board.setImg(file);
		
		BoardNoticeWriteProService boardWriteProService = new BoardNoticeWriteProService();
		boolean isWriteSuccess = boardWriteProService.registArticle(board);
		
		if(!isWriteSuccess) { 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('글 등록 실패!')"); 
			out.println("history.back()");
			out.println("</script>"); 
		} else { 
			forward = new ActionForward();
			forward.setPath("./AdminNoticeList.ad");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
