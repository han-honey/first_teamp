package vo;

import java.sql.Date;

/*
CREATE TABLE notice (
	idx INT PRIMARY KEY,
	name VARCHAR(16) NOT NULL,
	pass VARCHAR(16) NOT NULL,
	subject VARCHAR(100) NOT NULL,
	content VARCHAR(1000) NOT NULL,
	img VARCHAR(1000) NOT NULL,
	date DATETIME NOT NULL,	
	readcount INT NOT NULL
 );
*/
public class BoardDTO {
	private int idx;
	private String name;
	private String subject;
	private String content;
	private String img;
	private Date date;
	private int readcount;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	@Override
	public String toString() {
		return "BoardDTO [idx=" + idx + ", name=" + name + ", subject=" + subject + ", content=" + content + ", img="
				+ img + ", date=" + date + ", readcount=" + readcount + "]";
	}
	
	
}