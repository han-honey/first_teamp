package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ReviewWriteProService;
import vo.ActionForward;
import vo.ReviewDTO;

public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ReviewWriteProAction");
		
		ActionForward forward = null;
		
		String uploadPath = "/upload";
		int fileSize = 1024 * 1024 * 10;
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(uploadPath);
		
		MultipartRequest multi = new MultipartRequest(
				request, realPath,fileSize,"UTF-8", new DefaultFileRenamePolicy());
		
		ReviewDTO dto = new ReviewDTO();
		dto.setStar_scope(Integer.parseInt(multi.getParameter("star_scope")));
		dto.setMember_id(multi.getParameter("member_id"));
		dto.setReview_subject(multi.getParameter("review_subject"));
		dto.setReview_content(multi.getParameter("review_content"));
		dto.setProduct_code(multi.getParameter("product_code"));
		String fileElement = multi.getFileNames().nextElement().toString();
		String file = multi.getFilesystemName(fileElement);
		dto.setReview_img(file);
		
		System.out.println(dto.getStar_scope());
		System.out.println(multi.getParameter("product_code"));
		System.out.println(multi.getParameter("member_id"));
		ReviewWriteProService reviewWriteProService = new ReviewWriteProService();
		boolean isSuccess =reviewWriteProService.registArticle(dto);
		
		if(!isSuccess) {
			response.setContentType("text/html; charset=UTF-8 ");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('리뷰 등록 실패')");
			out.println("history.back()");
			out.println("</script>"); 
		} else {
			forward = new ActionForward();
			response.setContentType("text/html; charset=UTF-8 ");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('리뷰 등록 완료!\n 500포인트가 적립되었습니다.')");
			out.println("history.back()");
			out.println("</script>"); 
			forward.setPath("../payment/OrderList.pa");
			forward.setRedirect(true);
		}
		return forward;
	}

}
