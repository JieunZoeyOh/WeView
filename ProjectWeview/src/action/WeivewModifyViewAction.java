package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.CategoryDAO;
import dto.BoardDTO;
import dto.CategoryDTO;

public class WeivewModifyViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		CategoryDAO categorydao = new CategoryDAO();
		ActionForward forward = new ActionForward();
		
		List<CategoryDTO> categorylist = categorydao.getCategory();
		categorylist= categorydao.getCategory();
		request.setAttribute("category", categorylist);
		
		dto = dao.getDetail(no);
		
		if(dto == null) {
			System.out.println("상세보기 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("list", dto);
		forward.setRedirect(false);
		forward.setPath("weview/compare_modify.jsp");
		return forward;
	}
	
}
