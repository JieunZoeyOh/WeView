package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeDAO;
import dto.NoticeDTO;

public class AdminNoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeDAO noticedao = new NoticeDAO();
		NoticeDTO noticedata = new NoticeDTO();
		//글 번호 파라미터 값을 no 변수에 저장합니다.
		int no = Integer.parseInt(request.getParameter("no"));
		
		noticedata = noticedao.getDetail(no);
		
		//DAO에서 글의 내용을 읽지 못햇을 경우 null을 반환합니다.
		if(noticedata==null) {
			System.out.println("상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세 보기 성공");
		
		//noticedata 객체를 request 객체에 저장합니다.
		request.setAttribute("noticedata", noticedata);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		//글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("admin/adminNoticeDetail.jsp");
		return forward;
	}

}
