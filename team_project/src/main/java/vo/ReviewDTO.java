package vo;


import java.sql.Timestamp;

public class ReviewDTO {
	
	private int review_idx;
	private String product_code;
	private String member_id;
	private String review_subject;
	private String review_content;
	private String review_img;
	private int star_scope;
	private Timestamp review_date;
	
	public int getReview_idx() {
		return review_idx;
	}
	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
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
	public String getReview_subject() {
		return review_subject;
	}
	public void setReview_subject(String review_subject) {
		this.review_subject = review_subject;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_img() {
		return review_img;
	}
	public void setReview_img(String review_img) {
		this.review_img = review_img;
	}
	public int getStar_scope() {
		return star_scope;
	}
	public void setStar_scope(int star_scope) {
		this.star_scope = star_scope;
	}
	public Timestamp getReview_date() {
		return review_date;
	}
	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}
	@Override
	public String toString() {
		return "ReviewDTO [review_idx=" + review_idx + ", product_code=" + product_code + ", member_id=" + member_id
				+ ", review_subject=" + review_subject + ", review_content=" + review_content + ", review_img="
				+ review_img + ", star_scope=" + star_scope + ", review_date=" + review_date + "]";
	}

}