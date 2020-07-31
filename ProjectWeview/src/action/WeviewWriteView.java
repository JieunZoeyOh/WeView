package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dto.CategoryDTO;

public class WeviewWriteView implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CategoryDAO categorydao = new CategoryDAO();
		
		List<CategoryDTO> categorylist = categorydao.getCategory();
		categorylist= categorydao.getCategory();
		request.setAttribute("category", categorylist);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("weview/compare_write.jsp");
		return forward;
	}
}
