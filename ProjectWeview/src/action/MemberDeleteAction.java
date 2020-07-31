package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean result = false;
		boolean usercheck = false;
		String delete = request.getParameter("delete");
		String email = delete;

		dao.MemberDAO cdao = new MemberDAO();
		// 글 삭제 명령을 요청한 사용자가 글을 작성한 사용자인지 판단하기 위해
		// 입력한 비밀번호와 저장된 비밀번호를 비교하면 일치하면 삭제 합니다.

		System.out.println(request.getParameter("pwd"));
		usercheck = cdao.isCommWriter(request.getParameter("pwd"));

		System.out.println(usercheck);

		// 비밀번호가 일치하지 않는경우
		if (usercheck == false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		System.out.println(email);
		result = cdao.memberDelete(email);

		// 삭제 실패한경우
		if (result == false) {
			System.out.println("회원 삭제 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 삭제하지 못했습니다.");

			return forward;
		}
		// 삭제처리 성공한 경우 -글목록보기 요청부분
		System.out.println("회원 삭제 성공");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제 되었습니다.');");
		out.println("location.href='AdminMember.com';");
		out.println("</script>");
		out.close();
		return null;
	}
}