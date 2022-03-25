package vo;

public class Email_certificationDTO {

	private String member_id;
	private String member_email;
	private int certification_num;
	private String temp_password;
	
	
	
	public String getTemp_password() {
		return temp_password;
	}
	public void setTemp_password(String temp_password) {
		this.temp_password = temp_password;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public int getCertification_num() {
		return certification_num;
	}
	public void setCertification_num(int certification_num) {
		this.certification_num = certification_num;
	}
	
	
}
