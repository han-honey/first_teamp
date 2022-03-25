package vo;

import java.sql.Timestamp;

public class WishlistDTO {

	private String member_id;
	private String product_code;
	private int product_price;
	private String product_name;
	private String product_img;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	@Override
	public String toString() {
		return "WishlistDTO [member_id=" + member_id + ", product_code=" + product_code + ", product_price="
				+ product_price + ", product_name=" + product_name + ", product_img=" + product_img + "]";
	}
	
	
	
	
	
	
	

}
