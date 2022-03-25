package vo;

import java.util.Date;

public class BoardEventCommentDTO {
	private int event_comment_idx;
	private String event_comment_id;
	private String event_comment_content;
	private Date event_comment_date;
	private int comment_idx;
	
	public int getComment_idx() {
		return comment_idx;
	}
	public void setComment_idx(int comment_idx) {
		this.comment_idx = comment_idx;
	}
	public int getEvent_comment_idx() {
		return event_comment_idx;
	}
	public void setEvent_comment_idx(int event_comment_idx) {
		this.event_comment_idx = event_comment_idx;
	}
	public String getEvent_comment_id() {
		return event_comment_id;
	}
	public void setEvent_comment_id(String event_comment_id) {
		this.event_comment_id = event_comment_id;
	}
	public String getEvent_comment_content() {
		return event_comment_content;
	}
	public void setEvent_comment_content(String event_comment_content) {
		this.event_comment_content = event_comment_content;
	}
	public Date getEvent_comment_date() {
		return event_comment_date;
	}
	public void setEvent_comment_date(Date event_comment_date) {
		this.event_comment_date = event_comment_date;
	}
	@Override
	public String toString() {
		return "BoardEventCommentDTO [event_comment_idx=" + event_comment_idx + ", event_comment_id=" + event_comment_id
				+ ", event_comment_content=" + event_comment_content + ", event_comment_date=" + event_comment_date
				+ "]";
	}
	
	
}
