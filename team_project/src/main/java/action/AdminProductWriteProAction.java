package action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.AdminProductWriteProService;
import vo.ActionForward;
import vo.ProductBoardDTO;

public class AdminProductWriteProAction implements Action {
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("AdminProductWriteProAction");
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
		
		ProductBoardDTO dto = new ProductBoardDTO();
		//dto.setProduct_idx(Integer.parseInt(multi.getParameter("product_idx")));
		dto.setProduct_category1(multi.getParameter("product_category1"));
		dto.setProduct_category2(multi.getParameter("product_category2"));
		dto.setProduct_code(multi.getParameter("product_code"));
		dto.setProduct_name(multi.getParameter("product_name"));
		dto.setProduct_price(Integer.parseInt(multi.getParameter("product_price")));
		dto.setProduct_stock(Integer.parseInt(multi.getParameter("product_stock")));
		
		String filename;
		String filename2;

		Enumeration files = multi.getFileNames();
		
		String file = (String)files.nextElement();
	    filename = multi.getFilesystemName(file);
	    System.out.println(filename);

		String file2 = (String)files.nextElement();
	    filename2 = multi.getFilesystemName(file2);
	    System.out.println(filename2);

		
//		Enumeration files = multi.getFileNames();
//		
//		String name = (String)files.nextElement();
//		File file = multi.getFile(name);
//		String product_info_img = file.getName();
//		System.out.println("product_info_img:" + product_info_img);
//		
//		String name2 = (String)files.nextElement();
//		File file2 = multi.getFile(name2);
//		String product_img = file2.getName();
//		System.out.println("product_img:" + product_img);
		
		
//		System.out.println(multi.getFileNames().nextElement().toString());
//		String fileElement = multi.getFileNames().nextElement().toString();
//		System.out.println(fileElement);
//		String file1 = multi.getFilesystemName(fileElement);
//		
//		fileElement = multi.getFileNames().nextElement().toString();
//		System.out.println(fileElement);
//		String file2 = multi.getFilesystemName(fileElement);
		
		dto.setProduct_img(filename);
		dto.setProduct_info_img(filename2);
		
		
		AdminProductWriteProService adminProductWriteProService = new AdminProductWriteProService();
		boolean isWriteSuccess = adminProductWriteProService.registArticle(dto);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 등록 실패')"); 
			out.println("history.back()");
			out.println("</script>"); 
		} else { 
			forward = new ActionForward();
			forward.setPath("./AdminProductList.ad");
			forward.setRedirect(true);
		}
		return forward;
	}

}
















