package svc;

import java.sql.Connection;

import com.mysql.fabric.Response;

import dao.WishlistDAO;
import static db.JdbcUtil.*;
import vo.WishlistDTO;

public class PaymentWishlistAddService {

	public int isWishlistAddSuccess(WishlistDTO wishlistDTO) {
		
		System.out.println("WishlistAddService - isWishlistAddSuccess() 메서드 호출");
		System.out.println("WishlistAddService - wishlistDTO : " + wishlistDTO);
		
		int wishlistAddSuccess = 0;
		
		Connection con = getConnection();
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		boolean isWishlistDupCheck = wishlistDAO.wishlistDupCheck(wishlistDTO);
		System.out.println("WishlistAddService - isWishlistDupCheck 확인 : " + isWishlistDupCheck);
		
		if (isWishlistDupCheck) { // 중복인 경우
			wishlistAddSuccess = -1;
			
		} else {
			
			wishlistAddSuccess = wishlistDAO.addWishlist(wishlistDTO);
			System.out.println("WishlistAddService - isWishlistAddSuccess 확인 : " + wishlistAddSuccess);
			
			if(wishlistAddSuccess > 0) { // 위시리스트 담기 성공했을 경우(1)
				commit(con);
			} else { // (0)
				rollback(con);
			}
			
		}
		
		
		close(con);
		
		return wishlistAddSuccess;
	}
}
