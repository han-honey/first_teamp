package vo;

public class QnAReplyDTO {

	private int reply_idx;
	private String reply_member_id;
	private String reply_content;
	private int reply_ref;
	
	/**
	 * @return the reply_idx
	 */
	public int getReply_idx() {
		return reply_idx;
	}
	/**
	 * @return the reply_member_id
	 */
	public String getReply_member_id() {
		return reply_member_id;
	}
	/**
	 * @return the reply_content
	 */
	public String getReply_content() {
		return reply_content;
	}
	/**
	 * @return the reply_ref
	 */
	public int getReply_ref() {
		return reply_ref;
	}
	/**
	 * @param reply_idx the reply_idx to set
	 */
	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}
	/**
	 * @param reply_member_id the reply_member_id to set
	 */
	public void setReply_member_id(String reply_member_id) {
		this.reply_member_id = reply_member_id;
	}
	/**
	 * @param reply_content the reply_content to set
	 */
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	/**
	 * @param reply_ref the reply_ref to set
	 */
	public void setReply_ref(int reply_ref) {
		this.reply_ref = reply_ref;
	}
	
	
	
}
