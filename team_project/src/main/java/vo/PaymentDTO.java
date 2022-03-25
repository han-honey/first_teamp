package vo;

import java.sql.Timestamp;
import java.util.Arrays;

public class PaymentDTO {
//	+---------------------------+--------------+------+-----+---------+----------------+
//	| Field                     | Type         | Null | Key | Default | Extra          |
//	+---------------------------+--------------+------+-----+---------+----------------+
//	| order_idx                 | int          | NO   | PRI | NULL    | auto_increment |
//	| orderer_name              | varchar(100) | YES  |     | NULL    |                |
//	| receiver_name             | varchar(100) | YES  |     | NULL    |                |
//	| receiver_phone            | varchar(100) | YES  |     | NULL    |                |
//	| receiver_post             | int          | YES  |     | NULL    |                |
//	| receiver_address          | varchar(100) | YES  |     | NULL    |                |
//	| receiver_extra_Address    | varchar(100) | YES  |     | NULL    |                |
//	| order_product_code        | varchar(100) | YES  |     | NULL    |                |
//	| order_amount              | int          | YES  |     | NULL    |                |
//	| order_coupon              | varchar(100) | YES  |     | NULL    |                |
//	| before_discount_price     | int          | YES  |     | NULL    |                |
//	| after_discount_price      | int          | YES  |     | NULL    |                |
//	| order_status              | int          | YES  |     | NULL    |                |
//	| order_date                | timestamp    | YES  |     | NULL    |                |
//	| order_tracking_num        | varchar(100) | YES  |     | NULL    |                |
//	| order_request_message     | varchar(100) | YES  |     | NULL    |                |
//	| order_return_refund_check | int          | YES  |     | NULL    |                |
//	+---------------------------+--------------+------+-----+---------+----------------+
	private int order_idx;
	private String orderer_id;
	private String orderer_name;
	private String receiver_name;
	private String receiver_phone;
	private int receiver_post;
	private String receiver_address;
	private String receiver_extra_Address;
	private String[] order_product_code = new String[10];
	private int[] order_product_amount = new int[10];
	private String order_coupon;
	private int before_discount_price;
	private int after_discount_price;
	private String order_status;
	private Timestamp order_date;
	private String order_tracking_num;
	private String order_request_message;
	private int order_return_refund_check;
	private String prouct_name; // 상품리스트 출력을 위한 변수(DB에 없음)
	private int product_price; // 상품리스트 출력을 위한 변수(DB에 없음)
	
	public int getOrder_idx() {
		return order_idx;
	}
	public void setOrder_idx(int order_idx) {
		this.order_idx = order_idx;
	}
	public String getOrderer_name() {
		return orderer_name;
	}
	public void setOrderer_name(String orderer_name) {
		this.orderer_name = orderer_name;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public int getReceiver_post() {
		return receiver_post;
	}
	public void setReceiver_post(int receiver_post) {
		this.receiver_post = receiver_post;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public String getReceiver_extra_Address() {
		return receiver_extra_Address;
	}
	public void setReceiver_extra_Address(String receiver_extra_Address) {
		this.receiver_extra_Address = receiver_extra_Address;
	}
	public String[] getOrder_product_code() {
		return order_product_code;
	}
	public void setOrder_product_code(String[] order_product_code) {
		this.order_product_code = order_product_code;
	}
	public int[] getOrder_product_amount() {
		return order_product_amount;
	}
	public void setOrder_product_amount(int[] order_product_amount) {
		this.order_product_amount = order_product_amount;
	}
	public String getOrder_coupon() {
		return order_coupon;
	}
	public void setOrder_coupon(String order_coupon) {
		this.order_coupon = order_coupon;
	}
	public int getBefore_discount_price() {
		return before_discount_price;
	}
	public void setBefore_discount_price(int before_discount_price) {
		this.before_discount_price = before_discount_price;
	}
	public int getAfter_discount_price() {
		return after_discount_price;
	}
	public void setAfter_discount_price(int after_discount_price) {
		this.after_discount_price = after_discount_price;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public String getOrder_tracking_num() {
		return order_tracking_num;
	}
	public void setOrder_tracking_num(String order_tracking_num) {
		this.order_tracking_num = order_tracking_num;
	}
	public String getOrder_request_message() {
		return order_request_message;
	}
	public void setOrder_request_message(String order_request_message) {
		System.out.println("order_request_message(PaymentDTO에서 출력):" + order_request_message);
		this.order_request_message = order_request_message;
	}
	public int getOrder_return_refund_check() {
		return order_return_refund_check;
	}
	public void setOrder_return_refund_check(int order_return_refund_check) {
		this.order_return_refund_check = order_return_refund_check;
	}
	public void setOrder_product_code(String string) {
			order_product_code[0] += string;
	}
	public void setOrder_product_amount(int int1) {
			order_product_amount[0] += int1;
	}
	public String getOrderer_id() {
		return orderer_id;
	}
	public void setOrderer_id(String orderer_id) {
		this.orderer_id = orderer_id;
	}
	public String getProuct_name() {
		return prouct_name;
	}
	public void setProuct_name(String prouct_name) {
		this.prouct_name = prouct_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	@Override
	public String toString() {
		return "PaymentDTO [order_idx=" + order_idx + ", orderer_id=" + orderer_id + ", orderer_name=" + orderer_name
				+ ", receiver_name=" + receiver_name + ", receiver_phone=" + receiver_phone + ", receiver_post="
				+ receiver_post + ", receiver_address=" + receiver_address + ", receiver_extra_Address="
				+ receiver_extra_Address + ", order_product_code=" + Arrays.toString(order_product_code)
				+ ", order_product_amount=" + Arrays.toString(order_product_amount) + ", order_coupon=" + order_coupon
				+ ", before_discount_price=" + before_discount_price + ", after_discount_price=" + after_discount_price
				+ ", order_status=" + order_status + ", order_date=" + order_date + ", order_tracking_num="
				+ order_tracking_num + ", order_request_message(toString)=" + order_request_message
				+ ", order_return_refund_check=" + order_return_refund_check + ", prouct_name=" + prouct_name
				+ ", product_price=" + product_price + "]";
	}
	
	
	
	
}