package vo;

import java.security.Timestamp;

public class Earning_pointDTO {
//	+------------------------+---------------+------+-----+---------+-------+
//	| Field                  | Type          | Null | Key | Default | Extra |
//	+------------------------+---------------+------+-----+---------+-------+
//	| member_id              | varchar(1000) | NO   | PRI | NULL    |       |
//	| total_earning_point    | int			 | YES  |     | NULL    |       |
//	| earning_history        | varchar(1000) | YES  |     | NULL    |       |
//	| earning_change         | int			 | YES  |     | NULL    |       |
//	| earning_date           | timestamp	 | YES  |     | NULL    |       |
//	+------------------------+---------------+------+-----+---------+-------+
	private String member_id;
	private int total_earning_point;
	private String earning_history;
	private int earning_change;
	private Timestamp earning_date;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getTotal_earning_point() {
		return total_earning_point;
	}
	public void setTotal_earning_point(int total_earning_point) {
		this.total_earning_point = total_earning_point;
	}
	public String getEarning_history() {
		return earning_history;
	}
	public void setEarning_history(String earning_history) {
		this.earning_history = earning_history;
	}
	public int getEarning_change() {
		return earning_change;
	}
	public void setEarning_change(int earning_change) {
		this.earning_change = earning_change;
	}
	public Timestamp getEarning_date() {
		return earning_date;
	}
	public void setEarning_date(Timestamp earning_date) {
		this.earning_date = earning_date;
	}
	@Override
	public String toString() {
		return "Earning_pointDTO [member_id=" + member_id + ", total_earning_point=" + total_earning_point
				+ ", earning_history=" + earning_history + ", earning_change=" + earning_change + ", earning_date="
				+ earning_date + "]";
	}
	
	
}
