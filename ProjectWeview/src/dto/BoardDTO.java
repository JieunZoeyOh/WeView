package dto;

public class BoardDTO {
	private int no;
	private String title;
	private String content;
	private int category_no;
	private String member_email;
	private int report; //신고수
	private String regidate;
	private String image1;
	private String image2;
	private String image3;
	private String productName1;
	private String productName2;
	private String productName3;
	private int views; //조회수
	private int like1;
	private int like2;
	private int like3;
	private String best;
	
	public String getBest() {
		return best;
	}
	public void setBest(String best) {
		this.best = best;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public int getReport() {
		return report;
	}
	public void setReport(int report) {
		this.report = report;
	}
	public String getRegidate() {
		return regidate.substring(0, 16);
	}
	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getProductName1() {
		return productName1;
	}
	public void setProductName1(String productName1) {
		this.productName1 = productName1;
	}
	public String getProductName2() {
		return productName2;
	}
	public void setProductName2(String productName2) {
		this.productName2 = productName2;
	}
	public String getProductName3() {
		return productName3;
	}
	public void setProductName3(String productName3) {
		this.productName3 = productName3;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
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
