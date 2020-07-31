package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportBoardDAO;
import dto.ReportBoardDTO;

public class commAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ReportBoardDAO cdao = new ReportBoardDAO();

		List<ReportBoardDTO> c = cdao.getcomm();
		forward.setPath("admin/adminComment.jsp");
		forward.setRedirect(false);

		request.setAttribute("totallist", c);

		return forward;
	}
}
