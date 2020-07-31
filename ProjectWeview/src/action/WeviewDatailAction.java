package action;

import java.util.List;

import javax.mail.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dao.CategoryDAO;
import dao.CommDAO;
import dao.LoveDAO;
import dao.VoteDAO;
import dto.BoardDTO;
import dto.CategoryDTO;
import dto.CommDTO;
import dto.VoteDTO;

public class WeviewDatailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		CommDAO commdao = new CommDAO();
		CommDTO commdto = new CommDTO();
		CategoryDAO categorydao = new CategoryDAO();
		VoteDAO votedao = new VoteDAO();
		VoteDTO votedto = new VoteDTO();
		LoveDAO lovedao = new LoveDAO();
		
		List<CategoryDTO> categorylist = categorydao.getCategory();
		request.setAttribute("category", categorylist);
		
		votedto = votedao.getAllChart(no);
		request.setAttribute("vote", votedto);
		
		dao.setReadCountUpdate(no); // 조회수 증가
		dto = dao.getDetail(no);
		request.setAttribute("no", no);
		int board_no = dto.getNo();

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		System.out.println("no: "+no+", email:"+email);
		boolean lovecheck = lovedao.lovecheck(no,email);

		int listcount = commdao.getListCount(board_no);
		System.out.println("댓글수:"+listcount);
		List<CommDTO> commlist = commdao.getlist(board_no);
		System.out.println("commlist: "+commlist);
		System.out.println("dto?"+dto);
		if(dto == null || commdto == null) {
			System.out.println("상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("list", dto);
		request.setAttribute("comm", commlist);
		request.setAttribute("comm_total", listcount);
		request.setAttribute("lovecheck", lovecheck);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("weview/compare_detail.jsp");
		return forward;
	}
}
