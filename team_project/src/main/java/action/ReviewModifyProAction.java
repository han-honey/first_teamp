package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ReviewModifyProService;
import svc.ReviewWriteProService;
import vo.ActionForward;
import vo.ReviewDTO;

public class ReviewModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewModifyProAction");
		
		ActionForward forward = null;
		
		String uploadPath = "/upload";
		int fileSize = 1024 * 1024 * 10;
		ServletContext context =request.getServletContext();
		String realPath = context.getRealPath(uploadPath);
		
		MultipartRequest multi = new MultipartRequest(request, realPath, fileSize,"UTF-8",new DefaultFileRenamePolicy());
		
		ReviewDTO dto = new ReviewDTO();
		dto.setMember_id(multi.getParameter("member_id"));
		dto.setReview_idx(Integer.parseInt(multi.getParameter("review_idx")));
		dto.setStar_scope(Integer.parseInt(multi.getParameter("star_scope")));
		dto.setReview_subject(multi.getParameter("review_subject"));
		dto.setReview_content(multi.getParameter("review_content"));
		
		String fileElement = multi.getFileNames().nextElement().toString();
		String file = multi.getFilesystemName(fileElement);
		dto.setReview_img(file);
		
		ReviewModifyProService reviewModifyProService = new ReviewModifyProService();

		boolean isModifySuccess = reviewModifyProService.modifyArticle(dto);
			
			if(!isModifySuccess) { 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('리뷰 수정 실패')");
				out.println("history.back()");
				out.println("</script>"); 
				} else { 
				forward = new ActionForward();
				forward.setPath("ReviewContent.bo?review_idx=" + dto.getReview_idx());
				forward.setRedirect(true); 
			}
		return forward;
	}

}
