package action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import dto.BoardDTO;

public class WeviewModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		ActionForward forward = new ActionForward();
		
		String realFolder="";
		String saveFolder="boardupload";
		
		int fileSize=5*1024*1024;
		ServletContext sc =request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder = "+realFolder);
		boolean result = false;
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			int no = Integer.parseInt(multi.getParameter("no"));
			//BoardBean 객체에 글 등록 폼에서 입력받은 정보들을 저장합니다.
			System.out.println("no: "+no);
			dto.setNo(no);
			dto.setTitle(multi.getParameter("title"));
			dto.setCategory_no(Integer.parseInt(multi.getParameter("category")));
			System.out.println(Integer.parseInt(multi.getParameter("category")));
			dto.setMember_email(multi.getParameter("member_email"));
			dto.setContent(multi.getParameter("content"));
			dto.setProductName1(multi.getParameter("productName1"));
			dto.setProductName2(multi.getParameter("productName2"));
			dto.setProductName3(multi.getParameter("productName3"));
			String check1 = multi.getParameter("check1");
			String check2 = multi.getParameter("check2");
			String check3 = multi.getParameter("check3");
			System.out.println("check1="+check1+", check2="+check2+", check3="+check3);
			if(check1!=null) { //첨부파일을 변경하지 않은 경우
				dto.setImage1(check1);
			}else {
				// 업로드된 파일의 시스템 상에 업로드된 실제 파일명을 얻어옵니다.
				String filename1 = multi.getFilesystemName("image1");
				dto.setImage1(filename1);
			}if(check2!=null) { //첨부파일을 변경하지 않은 경우
				dto.setImage2(check1);
			}else {
				// 업로드된 파일의 시스템 상에 업로드된 실제 파일명을 얻어옵니다.
				String filename2 = multi.getFilesystemName("image2");
				dto.setImage2(filename2);
			}if(check3!=null) { //첨부파일을 변경하지 않은 경우
				dto.setImage3(check3);
			}else {
				// 업로드된 파일의 시스템 상에 업로드된 실제 파일명을 얻어옵니다.
				String filename3 = multi.getFilesystemName("image3");
				dto.setImage3(filename3);
			}
			//글 등록 처리를 위해 DAO의 boardInsert()메서드를 호출합니다.
			//글 등록 폼에서 입력한 정보가 저장되어있는 dto객체를 전달합니다.
			result=dao.boardModify(dto);
			
			//글 등록에 실패할 경우 false를 반환
			if(result==false) {
				System.out.println("개시판 수정 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시판 수정 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("게시판 수정 완료");
			
			// 글 등록이 완료되면 글 목록을 단순히 보여주기만 할 것이므로 Redirect 여부를 true로 설정
			forward.setRedirect(true);
			forward.setPath("WeviewDetail.com?no="+dto.getNo()); // 이동할 경로 지정
			return forward;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
}
