package action;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;

public class LoginProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		String email = request.getParameter("email");
		String IDStore = request.getParameter("remember");
		String pass = request.getParameter("pass");
		MemberDAO dao = new MemberDAO();

		int result = dao.isId(email, pass);
		
		System.out.println("결과는 "+result);
		
		if(result==1) {
			HttpSession session = request.getSession();
			//로그인 성공
			session.setAttribute("email", email);

			Cookie cookie = new Cookie("email",email);
			
			//email 기억하기를 체크한 경우
			if(IDStore != null && IDStore.equals("store")) {
				//cookie.setMaxAge(60*60*24); 쿠키의 유효시간 24시간
				cookie.setMaxAge(2*60);
				//클라이언트로 쿠키값 전송
				response.addCookie(cookie);
			} else {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			forward.setRedirect(true);
			forward.setPath("WeviewMain.com");
			if(email.trim().equals("admin")) forward.setPath("AdminBoard.com");
			return forward;
		}else {
			String message = "비밀번호가 일치하지 않습니다.";
			if(result==-1) 
				message = "이메일이 존재하지 않습니다.";
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+message+"');");
			out.println("location.href='./login.com';");
			out.println("</script>");
			out.close();
			return null;
		}
	}
	
	
}
