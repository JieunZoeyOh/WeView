package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportBoardDAO;

public class commDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean result = false;
		//boolean usercheck = false;
		String delete = request.getParameter("no");
		int no = Integer.parseInt(delete);
		System.out.println("board_no:"+no);

		dao.ReportBoardDAO cdao = new ReportBoardDAO();

		result = cdao.commDelete(no);

		// 삭제 실패한경우
		if (result == false) {

			System.out.println("댓글 삭제 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 삭제하지 못했습니다.");
			forward.setPath("error/erroe.jsp");
			return forward;

		}
		// 삭제처리 성공한 경우 -글목록보기 요청부분
		System.out.println("댓글 삭제 성공");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제 되었습니다.');");
		out.println("location.href='AdminComm.com';");
		out.println("</script>");
		out.close();
		return null;
	}
}
