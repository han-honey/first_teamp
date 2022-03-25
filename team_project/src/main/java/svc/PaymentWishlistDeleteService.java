package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.WishlistDAO;
import vo.WishlistDTO;

public class PaymentWishlistDeleteService {

	public boolean deleteWishlist(WishlistDTO wishlistDTO) {
		
		System.out.println("WishlistDeleteService - deleteWishlist() 메서드 호출");
		System.out.println("WishlistDeleteService - wishlistDTO : " + wishlistDTO);
		
		boolean isWishlistDeleteSuccess = false;
		
		Connection con = getConnection();
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		int deleteCount = 0;
		
		if (wishlistDTO.getProduct_code().equals("ALLDELETE")) {
			deleteCount = wishlistDAO.deleteAllWishlist(wishlistDTO);
			
		} else {
			deleteCount = wishlistDAO.deleteOneWishlist(wishlistDTO);
		}
		
		System.out.println("WishlistDeleteService - deleteCount 확인 : " + deleteCount);
		
		if(deleteCount > 0) {
			commit(con);
			isWishlistDeleteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isWishlistDeleteSuccess;
	}

}

