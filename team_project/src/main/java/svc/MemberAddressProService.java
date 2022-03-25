package svc;

import java.sql.Connection;

import dao.AddressDAO;
import static db.JdbcUtil.*;
import vo.AddressDTO;

public class MemberAddressProService {
	public boolean insertMemberAddress(AddressDTO addressDTO) {
		boolean isInsertAddress = false;
		System.out.println("MemberAddressProService-insertMemberAddress");
		
		Connection con = getConnection();
		AddressDAO addressDAO = AddressDAO.getInstance();
		addressDAO.setConnection(con);
		
		if(addressDTO.getAddress_default()==1) { //기본배송지로 등록하려고 할 때 
			int clearCount = addressDAO.clearDefault(addressDTO.getMember_id());
			System.out.println("기본배송지를 0으로 수정한 횟수 :" + clearCount);
		}
		int insertAddress = addressDAO.insertAddress(addressDTO);
		
		if(insertAddress > 0) {
			commit(con);
			isInsertAddress = true;
		} else {
			rollback(con);
		}
		
		close(con);
		return isInsertAddress;
	}
	
	public boolean deleteMemberAddress(int address_idx) {
		boolean isDeleteAddress = false;
		System.out.println("MemberAddressProService-deleteMemberAddress");
		
		Connection con = getConnection();
		AddressDAO addressDAO = AddressDAO.getInstance();
		addressDAO.setConnection(con);
		
		
		int deleteCount = addressDAO.deleteAddress(address_idx);
		
		if(deleteCount > 0) {
			isDeleteAddress = true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return isDeleteAddress;
	}
	
	public boolean updateMemberAddress(AddressDTO addressDTO) {
		boolean isUpdateAddress = false;
		System.out.println("MemberAddressProService-updateMemberAddress");
		
		Connection con = getConnection();
		AddressDAO addressDAO = AddressDAO.getInstance();
		addressDAO.setConnection(con);
		
		if(addressDTO.getAddress_default()==1) { //기본배송지로 등록하려고 할 때 
			int clearCount = addressDAO.clearDefault(addressDTO.getMember_id());
			System.out.println("기본배송지를 0으로 수정한 횟수 :" + clearCount);
		}
		
		int updateCount = addressDAO.updateAddressInfo(addressDTO);
		
		if(updateCount > 0) {
			isUpdateAddress = true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return isUpdateAddress;
	}
}
