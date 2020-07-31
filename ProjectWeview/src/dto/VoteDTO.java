package dto;

public class VoteDTO {
	private int no;
	private int board_no;
	private String member_email;
	private int like1;
	private int like2;
	private int like3;
	private MemberDTO dtoMember;
	
	public MemberDTO getDtoMember() {
		return dtoMember;
	}
	public void setDtoMember(MemberDTO dtoMember) {
		this.dtoMember = dtoMember;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public int getLike1() {
		return like1;
	}
	public void setLike1(int like1) {
		this.like1 = like1;
	}
	public int getLike2() {
		return like2;
	}
	public void setLike2(int like2) {
		this.like2 = like2;
	}
	public int getLike3() {
		return like3;
	}
	public void setLike3(int like3) {
		this.like3 = like3;
	}
}
