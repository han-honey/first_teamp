package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardNoticeModifyProService;
import vo.ActionForward;
import vo.BoardDTO;

public class BoardNoticeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardNoticeModifyProAction");
		
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
		board.setIdx(Integer.parseInt(multi.getParameter("idx")));
		board.setName(multi.getParameter("name"));
		board.setSubject(multi.getParameter("contentTitle"));
		board.setContent(multi.getParameter("contentDetail"));
		
		String fileElement = multi.getFileNames().nextElement().toString();
		
		String file = multi.getFilesystemName(fileElement);
		
		board.setImg(file);
		BoardNoticeModifyProService boardNoticeModifyProService = new BoardNoticeModifyProService();
		boolean isModifySuccess = boardNoticeModifyProService.modifyArticle(board);
			
		if(!isModifySuccess) { 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('글 수정 실패!')");
			out.println("history.back()");
			out.println("</script>"); 
		} else { 
			forward = new ActionForward();
			forward.setPath("NoticeContent.bo?idx=" + board.getIdx() + "&page=" + request.getParameter("page"));
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
