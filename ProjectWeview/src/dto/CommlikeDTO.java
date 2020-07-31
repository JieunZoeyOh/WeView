package dto;

public class CommlikeDTO {
	private int no;
	private int comm_no;
	private String member_email;
	private int love;
	private int hate;
	private CommDTO dtoComm;
	
	public CommDTO getDtoComm() {
		return dtoComm;
	}
	public void setDtoComm(CommDTO dtoComm) {
		this.dtoComm = dtoComm;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getComm_no() {
		return comm_no;
	}
	public void setComm_no(int comm_no) {
		this.comm_no = comm_no;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
	}
	public int getHate() {
		return hate;
	}
	public void setHate(int hate) {
		this.hate = hate;
	}
}
