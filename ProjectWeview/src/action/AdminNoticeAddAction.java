package action;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.NoticeDAO;
import dto.NoticeDTO;

public class AdminNoticeAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeDAO noticedao = new NoticeDAO();
		NoticeDTO noticedata = new NoticeDTO();
		ActionForward forward = new ActionForward();
		
		String realFolder="";
		String saveFolder = "noticeupload";
		
		int fileSize = 5*1024*1024;//업로드할 파일의 최대 사이즈 입니다. 5MB
		
		//실제 저장 경로를 지정합니다.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		
		System.out.println("realFolder = " + realFolder);
		boolean result = false;
		
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, 
					realFolder, 
					fileSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			//BoardBean 객체에 글 등록 폼에서 입력 받은 정보들을 저장합니다.
			noticedata.setTitle(multi.getParameter("title"));
			noticedata.setContent(multi.getParameter("content"));
			noticedata.setImage1(multi.getFilesystemName("image1"));
			noticedata.setImage2(multi.getFilesystemName("image2"));
			noticedata.setImage3(multi.getFilesystemName("image3"));

			//글 등록 처리를 위해 DAO의 noticeInsert() 메서드를 호출합니다.
			//글 등록 폼에서 입력한 정보가 저장되어 있는 noticedata 객체를 전달합니다.
			result = noticedao.noticeInsert(noticedata);
			
			//글 등록에 실패할 경우 false를 반환합니다.
			if(result==false) {
				System.out.println("게시판 등록 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시판 등록 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("게시판 등록 완료");
			
			//글 등록이 완료되는 글 목록을 단순히 보여주기만 할 것 이므로 Redirect여부를 true로 설정합니다.
			forward.setRedirect(true);
			
			forward.setPath("AdminNoticeList.com");
			return forward;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
