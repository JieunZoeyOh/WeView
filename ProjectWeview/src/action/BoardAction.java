package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		BoardDAO bdao = new BoardDAO();

		List<BoardDTO> b = bdao.getList();
		forward.setPath("admin/adminBoardList.jsp");
		forward.setRedirect(false);
		request.setAttribute("totallist", b);
		return forward;
	}
}
