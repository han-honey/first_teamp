package vo;

import java.sql.Date;

public class QnADTO {

	private int question_idx;
	private String question_category;
	private String product_code;
	private String member_id;
	private String question_subject;
	private String question_content;
	private String question_password;
	private String question_img;
	private Date question_date; 
	private int answer_ref;
	private int answer_lev;
	private int answer_seq;
	public int getQuestion_idx() {
		return question_idx;
	}
	public void setQuestion_idx(int question_idx) {
		this.question_idx = question_idx;
	}
	
	public String getQuestion_category() {
		return question_category;
	}
	public void setQuestion_category(String question_category) {
		this.question_category = question_category;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getQuestion_subject() {
		return question_subject;
	}
	public void setQuestion_subject(String question_subject) {
		this.question_subject = question_subject;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public String getQuestion_password() {
		return question_password;
	}
	public void setQuestion_password(String question_password) {
		this.question_password = question_password;
	}
	public String getQuestion_img() {
		return question_img;
	}
	public void setQuestion_img(String question_img) {
		this.question_img = question_img;
	}
	public Date getQuestion_date() {
		return question_date;
	}
	public void setQuestion_date(Date question_date) {
		this.question_date = question_date;
	}
	public int getAnswer_ref() {
		return answer_ref;
	}
	public void setAnswer_ref(int answer_ref) {
		this.answer_ref = answer_ref;
	}
	public int getAnswer_lev() {
		return answer_lev;
	}
	public void setAnswer_lev(int answer_lev) {
		this.answer_lev = answer_lev;
	}
	public int getAnswer_seq() {
		return answer_seq;
	}
	public void setAnswer_seq(int answer_seq) {
		this.answer_seq = answer_seq;
	}
	
	@Override
	public String toString() {
		return "QnADTO [question_idx=" + question_idx + ", question_category=" + question_category + ", product_code="
				+ product_code + ", member_id=" + member_id + ", question_subject=" + question_subject
				+ ", question_content=" + question_content + ", question_password=" + question_password
				+ ", question_img=" + question_img + ", question_date=" + question_date + ", answer_ref=" + answer_ref
				+ ", answer_lev=" + answer_lev + ", answer_seq=" + answer_seq + "]";
	}
	
}
