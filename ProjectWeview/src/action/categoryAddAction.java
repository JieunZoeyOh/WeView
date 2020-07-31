package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.CategoryDAO;
import dto.CategoryDTO;

public class categoryAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CategoryDAO catdao = new CategoryDAO();
		CategoryDTO catdto = new CategoryDTO();
		ActionForward forward = new ActionForward();
		boolean result = false;

		catdto.setName(request.getParameter("category"));

		result = catdao.categoryInsert(catdto);

		// 카테고리 실패 할 경우 false를 반환 합니다.

		if (result == false) {

			System.out.println("카테고리 등록 실패");
			request.setAttribute("message", "게시판 등록 실패");
			forward.setRedirect(false);
			return forward;

		}
		System.out.println("게시판 등록완료");

		// 글 등록이 완료되면 글 목록을 단순히 보여주김나 할것이므로 Redirect여부를 true로 설정합니다.
		forward.setRedirect(true);

		forward.setPath("Admincategory.com");
		return forward;

	}

}
