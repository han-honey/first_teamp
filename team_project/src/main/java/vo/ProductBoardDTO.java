package vo;

import java.sql.Timestamp;
//+-----------------------+--------------+------+-----+---------+----------------+
//| Field                 | Type         | Null | Key | Default | Extra          |
//+-----------------------+--------------+------+-----+---------+----------------+
//| product_idx           | int          | NO   | PRI | NULL    | auto_increment |
//| product_category1     | varchar(100) | YES  |     | NULL    |                |
//| product_category2     | varchar(100) | YES  |     | NULL    |                |
//| product_code          | varchar(100) | YES  |     | NULL    |                |
//| product_name          | varchar(100) | YES  |     | NULL    |                |
//| product_img           | varchar(100) | YES  |     | NULL    |                |
//| product_price         | int          | YES  |     | NULL    |                |
//| product_date          | timestamp    | YES  |     | NULL    |                |
//| product_stock         | int          | YES  |     | NULL    |                |
//| product_sell_amount   | int          | YES  |     | NULL    |                |
//| product_refund_amount | int          | YES  |     | NULL    |                |
//+-----------------------+--------------+------+-----+---------+----------------+
public class ProductBoardDTO {

	private int product_idx;
	private String product_category1;
	private String product_category2;
	private String product_code;
	private String product_name;
	private String product_img;
	private String product_info_img;
	private int product_price;
	private Timestamp product_date;
	private int product_stock;
	private int product_sell_amount;
	private int product_refund_amount;
	private int avg_star;
	public int getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	public String getProduct_category1() {
		return product_category1;
	}
	public void setProduct_category1(String product_category1) {
		this.product_category1 = product_category1;
	}
	public String getProduct_category2() {
		return product_category2;
	}
	public void setProduct_category2(String product_category2) {
		this.product_category2 = product_category2;
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
	public String getProduct_info_img() {
		return product_info_img;
	}
	public void setProduct_info_img(String product_info_img) {
		this.product_info_img = product_info_img;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public Timestamp getProduct_date() {
		return product_date;
	}
	public void setProduct_date(Timestamp product_date) {
		this.product_date = product_date;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	public int getProduct_sell_amount() {
		return product_sell_amount;
	}
	public void setProduct_sell_amount(int product_sell_amount) {
		this.product_sell_amount = product_sell_amount;
	}
	public int getProduct_refund_amount() {
		return product_refund_amount;
	}
	public void setProduct_refund_amount(int product_refund_amount) {
		this.product_refund_amount = product_refund_amount;
	}
	public int getAvg_star() {
		return avg_star;
	}
	public void setAvg_star(int avg_star) {
		this.avg_star = avg_star;
	}
	@Override
	public String toString() {
		return "ProductBoardDTO [product_idx=" + product_idx + ", product_category1=" + product_category1
				+ ", product_category2=" + product_category2 + ", product_code=" + product_code + ", product_name="
				+ product_name + ", product_img=" + product_img + ", product_info_img=" + product_info_img
				+ ", product_price=" + product_price + ", product_date=" + product_date + ", product_stock="
				+ product_stock + ", product_sell_amount=" + product_sell_amount + ", product_refund_amount="
				+ product_refund_amount + ", avg_star=" + avg_star + "]";
	}
	
}