package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AddressDAO;
import dao.MemberDAO;
import vo.AddressDTO;
import vo.MemberDTO;

public class MemberAddressListService {

	public ArrayList<AddressDTO> selectMemberAddress(String id) {
		System.out.println("MemberAddressListService-selectMemberAddress()"+"아이디 : " + id);
		ArrayList<AddressDTO> addressList = null;
		
		Connection con = getConnection();
		
		AddressDAO addressDAO = AddressDAO.getInstance();
		
		addressDAO.setConnection(con);
		
		addressList = addressDAO.selectAddressList(id);
		
		System.out.println(addressList);
		close(con);
		return addressList;
	}
	
	public AddressDTO selectMemberAddressOne(int address_idx) {
		System.out.println("MemberAddressListService-selectMemberAddressOne()");
		AddressDTO addressDTO = null;
		
		Connection con = getConnection();
		
		AddressDAO addressDAO = AddressDAO.getInstance();
		
		addressDAO.setConnection(con);
		
		addressDTO = addressDAO.selectAddress(address_idx);
		
		close(con);
		return addressDTO;
	}
	
}
