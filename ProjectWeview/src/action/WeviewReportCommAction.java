package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportBoardDAO;
import dto.ReportBoardDTO;

public class WeviewReportCommAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		ReportBoardDAO dao = new ReportBoardDAO();
		ReportBoardDTO dto = new ReportBoardDTO();
		
		int cno= Integer.parseInt(request.getParameter("commno"));
		int bno= Integer.parseInt(request.getParameter("bno"));
		String email = request.getParameter("memail");
		String reason = request.getParameter("reason");
		
		boolean check = false;
		
		dto.setBoard_no(bno);
		dto.setComm_no(cno);
		dto.setMember_email(email);
		dto.setReason_report(reason);
		check = dao.reportcommcheck(email, cno);

		if(check==false) {
			dao.commInsert(dto);
		}

		// 글 등록이 완료되면 글 목록을 단순히 보여주김나 할것이므로 Redirect여부를 true로 설정합니다.
		forward.setRedirect(true);

		forward.setPath("WeviewDetail.com?no="+bno);
		return forward;
	}
}
