package vo;

import java.sql.Timestamp;

public class MemberDTO {
//	+------------------------+---------------+------+-----+---------+-------+
//	| Field                  | Type          | Null | Key | Default | Extra |
//	+------------------------+---------------+------+-----+---------+-------+
//	| member_id              | varchar(1000) | NO   | PRI | NULL    |       |
//	| member_name            | varchar(1000) | YES  |     | NULL    |       |
//	| member_password        | varchar(1000) | YES  |     | NULL    |       |
//	| member_birth           | varchar(1000) | YES  |     | NULL    |       |
//	| member_phone           | varchar(1000) | YES  |     | NULL    |       |
//	| member_email           | varchar(1000) | YES  |     | NULL    |       |
//	| member_joinDate        | timestamp     | YES  |     | NULL    |       |
//	| member_total_purchase  | int           | YES  |     | NULL    |       |
//	| member_marketing_agree | varchar(1000) | YES  |     | NULL    |       |
//	| recent_login_date      | timestamp     | YES  |     | NULL    |       |
//	+------------------------+---------------+------+-----+---------+-------+
	private String member_id;
	private String member_name;
	private String member_password;
	private String member_birth;
	private String member_phone;
	private String member_email;
	private Timestamp member_joinDate;
	private int member_total_purchase;
	private String member_marketing_agree;
	private Timestamp recent_login_date;
	private String membership_grade;
	private String earning_point;
	
	//----------------관리자페이지의 회원리스트 조회 시 필요한 주소 정보 변수
	private int address_post;
	private String address;
	private String extra_address;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_birth() {
		return member_birth;
	}
	public void setMember_birth(String member_birth) {
		this.member_birth = member_birth;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public Timestamp getMember_joinDate() {
		return member_joinDate;
	}
	public void setMember_joinDate(Timestamp member_joinDate) {
		this.member_joinDate = member_joinDate;
	}
	public int getMember_total_purchase() {
		return member_total_purchase;
	}
	public void setMember_total_purchase(int member_total_purchase) {
		this.member_total_purchase = member_total_purchase;
	}
	public String getMember_marketing_agree() {
		return member_marketing_agree;
	}
	public void setMember_marketing_agree(String member_marketing_agree) {
		this.member_marketing_agree = member_marketing_agree;
	}
	public Timestamp getRecent_login_date() {
		return recent_login_date;
	}
	public void setRecent_login_date(Timestamp recent_login_date) {
		this.recent_login_date = recent_login_date;
	}
	
	
	public String getMembership_grade() {
		return membership_grade;
	}
	public void setMembership_grade(String membership_grade) {
		this.membership_grade = membership_grade;
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
	
	
	public String getEarning_point() {
		return earning_point;
	}
	public void setEarning_point(String earning_point) {
		this.earning_point = earning_point;
	}
	@Override
	public String toString() {
		return "MemberDTO [member_id=" + member_id + ", member_name=" + member_name + ", member_password="
				+ member_password + ", member_birth=" + member_birth + ", member_phone=" + member_phone
				+ ", member_email=" + member_email + ", member_joinDate=" + member_joinDate + ", member_total_purchase="
				+ member_total_purchase + ", member_marketing_agree=" + member_marketing_agree + ", recent_login_date="
				+ recent_login_date + ", membership_grade=" + membership_grade + "]";
	}
	
	
	
}
