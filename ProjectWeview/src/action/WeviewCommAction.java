package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.CommDAO;
import dto.CategoryDTO;
import dto.CommDTO;

public class WeviewCommAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
		CommDAO dao = new CommDAO();
		CommDTO dto = new CommDTO();
		CategoryDAO categorydao = new CategoryDAO();
		
		List<CategoryDTO> categorylist = categorydao.getCategory();
		categorylist= categorydao.getCategory();
		request.setAttribute("category", categorylist);
		
		ActionForward forward = new ActionForward();
		
		int no= Integer.parseInt(request.getParameter("board_no"));
		boolean result = false;
		
		String email = request.getParameter("comm_email");
		dto.setBoard_no(no);
		dto.setContent(request.getParameter("content"));
		dto.setMember_email(email);

		result = dao.commInsert(dto);
		
		System.out.println("결과:"+result);
		request.setAttribute("result", result);

		forward.setRedirect(true);
		forward.setPath("WeviewDetail.com?no="+no); // 이동할 경로 지정
		return forward;
	}
}
