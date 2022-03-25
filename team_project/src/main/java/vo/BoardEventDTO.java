package vo;

import java.sql.Date;

public class BoardEventDTO {

	private int event_idx;
	private String event_name;
	private String event_subject;
	private String event_content;
	private String event_img;
	private int event_readcount;
	private Date event_date;
	
	public int getEvent_idx() {
		return event_idx;
	}
	public void setEvent_idx(int event_idx) {
		this.event_idx = event_idx;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_subject() {
		return event_subject;
	}
	public void setEvent_subject(String event_subject) {
		this.event_subject = event_subject;
	}
	public String getEvent_content() {
		return event_content;
	}
	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}
	public String getEvent_img() {
		return event_img;
	}
	public void setEvent_img(String event_img) {
		this.event_img = event_img;
	}
	public int getEvent_readcount() {
		return event_readcount;
	}
	public void setEvent_readcount(int event_readcount) {
		this.event_readcount = event_readcount;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	
	@Override
	public String toString() {
		return "BoardEventDTO [event_idx=" + event_idx + ", event_name=" + event_name + ", event_subject="
				+ event_subject + ", event_content=" + event_content + ", event_img=" + event_img + ", event_readcount="
				+ event_readcount + ", event_date=" + event_date + "]";
	}
	
}