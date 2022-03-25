package vo;

public class AddressDTO {
//	+-------------------------+---------------+------+-----+---------+-------+
//	| Field                   | Type          | Null | Key | Default | Extra |
//	+-------------------------+---------------+------+-----+---------+-------+
//	| address_idx             | int           | NO   | PRI | NULL    |       |
//	| member_id               | varchar(1000) | YES  |     | NULL    |       |
//	| member_address_sequence | varchar(1000) | YES  |     | NULL    |       |
//	| address_post            | int           | YES  |     | NULL    |       |
//	| address                 | varchar(1000) | YES  |     | NULL    |       |
//	| extra_address           | varchar(1000) | YES  |     | NULL    |       |
//	| request_message         | varchar(1000) | YES  |     | NULL    |       |
//	| address_default         | int           | YES  |     | NULL    |       |
//	+-------------------------+---------------+------+-----+---------+-------+
	int address_idx;
	String member_id;
	String member_address_sequence;
	int address_post;
	String address;
	String extra_address;
	String request_message;
	int address_default;
	
	public int getAddress_idx() {
		return address_idx;
	}
	public void setAddress_idx(int address_idx) {
		this.address_idx = address_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_address_sequence() {
		return member_address_sequence;
	}
	public void setMember_address_sequence(String member_address_sequence) {
		this.member_address_sequence = member_address_sequence;
	}
	public int getAddress_post() {
		return address_post;
	}
	public void setAddress_post(int address_post) {
		this.address_post = address_post;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getExtra_address() {
		return extra_address;
	}
	public void setExtra_address(String extra_address) {
		this.extra_address = extra_address;
	}
	public String getRequest_message() {
		return request_message;
	}
	public void setRequest_message(String request_message) {
		this.request_message = request_message;
	}
	public int getAddress_default() {
		return address_default;
	}
	public void setAddress_default(int address_default) {
		this.address_default = address_default;
	}
	@Override
	public String toString() {
		return "Address_list [address_idx=" + address_idx + ", member_id=" + member_id + ", member_address_sequence="
				+ member_address_sequence + ", address_post=" + address_post + ", address=" + address
				+ ", extra_address=" + extra_address + ", request_message=" + request_message + ", address_default="
				+ address_default + "]";
	}
	
	
}
