package vo;
//create table cart(
//cart_idx int primary key auto_increment,
//member_id varchar(100),
//product_code varchar(100),
//product_amount int,
//product_price int);
public class CartUnknownDTO {
//	+----------------+--------------+------+-----+---------+----------------+
//	| Field          | Type         | Null | Key | Default | Extra          |
//	+----------------+--------------+------+-----+---------+----------------+
//	| cart_idx       | int          | NO   | PRI | NULL    | auto_increment |
//	| member_id      | varchar(100) | YES  |     | NULL    |                |
//	| product_idx    | varchar(100) | YES  |     | NULL    |                |
//	| product_amount | int          | YES  |     | NULL    |                |
//	| product_price  | int          | YES  |     | NULL    |                |
//	+----------------+--------------+------+-----+---------+----------------+
	int cart_idx;
	String member_id;
	String product_code;
	String product_name;
	String product_img;
	int product_amount;
	int product_price;
	
	public int getCart_idx() {
		return cart_idx;
	}
	public void setCart_idx(int cart_idx) {
		this.cart_idx = cart_idx;
	}
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
	public int getProduct_amount() {
		return product_amount;
	}
	public void setProduct_amount(int product_amount) {
		this.product_amount = product_amount;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	
	@Override
	public String toString() {
		return "CartDTO [cart_idx=" + cart_idx + ", member_id=" + member_id + ", product_code=" + product_code
				+ ", product_name=" + product_name + ", product_img=" + product_img + ", product_amount="
				+ product_amount + ", product_price=" + product_price + "]";
	}
	
}
