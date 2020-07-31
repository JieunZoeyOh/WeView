package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportBoardDAO;

public class AdminReportOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean result = false;	
		ReportBoardDAO reportdao = new ReportBoardDAO();
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		result = reportdao.reportOk(board_no);
		
		
		//삭제 처리 실패한 경우
			if(result == false) {
				System.out.println("신고 반려 실패");
				ActionForward forward = new ActionForward();
				forward.setRedirect(false);
				request.setAttribute("message", "반려하지 못했습니다.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			
			//삭제 처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
			System.out.println("신고 반려 성공");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('신고 반려 완료 되었습니다.');");
			out.println("location.href='AdminReport.com';");
			out.println("</script>");
			out.close();
			return null;
	}

}
