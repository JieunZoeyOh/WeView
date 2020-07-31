package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dto.CategoryDTO;

public class categoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		CategoryDAO ctdao = new CategoryDAO();

		List<CategoryDTO> c = ctdao.getCategory();
		forward.setPath("admin/adminCategory.jsp");
		forward.setRedirect(false);

		request.setAttribute("totallist", c);

		return forward;

	}

}
