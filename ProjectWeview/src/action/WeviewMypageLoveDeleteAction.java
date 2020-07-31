package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoveDAO;

public class WeviewMypageLoveDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		boolean result = false;
		LoveDAO lovedao = new LoveDAO();
		
		result = lovedao.myLoveDelete(email, no);
		
		if(result == false) {
			System.out.println("좋아요 취소 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "좋아요 취소하지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("좋아요 취소 성공");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('좋아요를 취소했습니다.');");
		out.println("location.href='WeviewMypageLove.com';");
		out.println("</script>");
		out.close();
		return null;
	}

}
