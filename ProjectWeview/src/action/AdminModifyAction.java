package action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.NoticeDAO;
import dto.NoticeDTO;

public class AdminModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeDAO noticedao = new NoticeDAO();
		NoticeDTO noticedata = new NoticeDTO();
		ActionForward forward = new ActionForward();
		String realFolder="";
		String saveFolder="noticeupload";
		
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
			int no = Integer.parseInt(multi.getParameter("no"));			
			noticedata.setNo(no);
			noticedata.setTitle(multi.getParameter("title"));
			noticedata.setContent(multi.getParameter("content"));
			noticedata.setRegidate(multi.getParameter("regidate"));
			
			String check1 = multi.getParameter("check1");
			String check2 = multi.getParameter("check2");
			String check3 = multi.getParameter("check3");
			System.out.println("check1 = " + check1);
			System.out.println("check2 = " + check2);
			System.out.println("check3 = " + check3);
			
			if(check1!=null) {	//기존파일 그대로 사용하는 경우입니다.
				noticedata.setImage1(check1);
			}else { //업로드된 파일의 시스템 상에 업로드된 실제 파일명을 얻어 옵니다.
				String filename1 = multi.getFilesystemName("image1");
				noticedata.setImage1(filename1);
			}
			if(check2!=null) {	
				noticedata.setImage2(check2);
			}else {				
				String filename2 = multi.getFilesystemName("image2");
				noticedata.setImage2(filename2);
			}
			if(check3!=null) {
				noticedata.setImage3(check3);
			}else {
				String filename3 = multi.getFilesystemName("image3");
				noticedata.setImage3(filename3);
			}
			//DAO에서 수정 메서드 호출하여 수정합니다.
			result = noticedao.noticeModify(noticedata);
			
			//수정에 실패할 경우
			if(result==false) {
				System.out.println("게시판 수정 실패");
				forward=new ActionForward();
				forward.setRedirect(false);
				request.setAttribute("message", "게시판 수정이 되지 않았습니다.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			//수정 성공의 경우
			System.out.println("게시판 수정 완료");
			
			forward.setRedirect(true);
			//수정한 글 내용을 보여주기 위해 글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
			forward.setPath("AdminNoticeDetailAction.com?no="+noticedata.getNo());
			return forward;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}