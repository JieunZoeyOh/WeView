package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommDAO;

public class WeviewCommDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("no: "+request.getParameter("no")+", board_no: "+request.getParameter("bno"));
		int no = Integer.parseInt(request.getParameter("no"));
		int b_no = Integer.parseInt(request.getParameter("bno"));
		CommDAO dao = new CommDAO();
			
		// 비밀번호 일치시 삭제 처리
		int result = dao.Delete(no);

		// 삭제 처리실패
		if(result==-1) {
			System.out.println("댓글 삭제 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 삭제하지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		//삭제 처리성공
		System.out.println("댓글 삭제 성공");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제 되었습니다.');");
		out.println("location.href='WeviewDetail.com?no="+b_no+"';");
		out.println("</script>");
		out.close();
		
		return null;
	}

}
