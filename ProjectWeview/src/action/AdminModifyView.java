package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeDAO;
import dto.NoticeDTO;

public class AdminModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		NoticeDAO noticedao = new NoticeDAO();
		NoticeDTO noticedata = new NoticeDTO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		noticedata = noticedao.getDetail(no);
		
		if(noticedata == null) {
			System.out.println("(수정)상세보기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정 상세 보기 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;			
		}
		System.out.println("(수정)상세보기 성공");
		
		request.setAttribute("noticedata", noticedata);
		forward.setRedirect(false);
		//글 수정 폼 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("admin/adminNoticeModify.jsp");
		return forward;
	}

}
