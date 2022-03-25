package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.WishlistDAO;
import vo.WishlistDTO;

public class PaymentWishlistService {

	public ArrayList<WishlistDTO> getWishlist(String member_id) {
		
		System.out.println("WishlistService - getWishList() 메서드 호출");
		System.out.println("WishlistService - member_id : " + member_id);
		
		ArrayList<WishlistDTO> wishlist = null;
		
		Connection con = getConnection();
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		wishlist = wishlistDAO.selectWishlist(member_id);

		close(con);
		
		System.out.println("WishlistService - DAO 파일에서 담아온 wishlist : " + wishlist);
		
		return wishlist;
		
	}

}
