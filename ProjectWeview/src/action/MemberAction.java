package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();

		List<MemberDTO> m = mdao.getList();
		forward.setPath("admin/adminMemberList.jsp");
		forward.setRedirect(false);

		request.setAttribute("totallist", m);

		return forward;
	}
}
