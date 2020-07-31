package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dto.BoardDTO;
public class WeviewMypageWrtittenAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		//로그인 성공시 파라미터 page가 없어요. 그래서 초기값이 필요.
		int page = 1;	//보여줄 page
		int limit = 10;	//한 페이지에 보여줄 게시판 목록의 수
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit = " + limit);
		
		//총 리스트 수를 받아옵니다.
		int listcount = boarddao.getWrittenListCount(email);
		
		//리스트를 받아옵니다.
		boardlist = boarddao.getWrittenList(email, page, limit);
		
		int maxpage = (listcount + limit -1 ) / limit;
		System.out.println("총 페이지 수 = " + maxpage);
		
		int startpage = ((page -1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 =" + startpage);
		
		//endpage: 현재 페이지 그룹에서 보여줄 마지막 페이지 수([10],[20],[30] 등...)
		int endpage=startpage + 10 - 1 ;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		
		if(endpage > maxpage) {
			endpage = maxpage;
		}
		
		request.setAttribute("page", page); //현재 페이지 수
		request.setAttribute("maxpage", maxpage); //최대 페이지 수
		
		//현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);
		
		//현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount); //총 글의 수
		
		//해당 페이지의 글 목록을 갖고 있는 리스트
		request.setAttribute("boardlist", boardlist);
		
		request.setAttribute("limit", limit);			
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		//글 목록 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("weview/mypage_board_written.jsp");
		return forward; 
	}

}
