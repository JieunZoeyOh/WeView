package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoveDAO;
import dto.LoveDTO;

public class WeviewLoveAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoveDAO dao = new LoveDAO();
		LoveDTO dto = new LoveDTO();
		
		int bno= Integer.parseInt(request.getParameter("bno"));
		String email = request.getParameter("memail");
		System.out.println("bno: "+bno+", email:"+email);
		boolean check = false;
		boolean result = false;
		
		dto.setBoard_no(bno);
		dto.setMember_email(email);
		check = dao.lovecheck(bno, email);
		System.out.println("값"+check);
		if(check==false) result = dao.insert(dto);
		else result = dao.delete(bno,email);
		
		if (result == false) {
			System.out.println("찜하기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			return forward;
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("location.href='WeviewDetail.com?no="+bno+"';");
		out.println("</script>");
		out.close();
		return null;
	}
	
}
