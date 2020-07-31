package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyPageDAO;
import dto.BoardDTO;


public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MyPageDAO boarddao = new MyPageDAO();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();

		// 로그인 성공시 파라미터 page가 없어요. 그래서 초기값 필요/
		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 =" + page);

		if (request.getParameter("limit") != null) {

			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit =" + limit);

		// 총 리스트 수를 받아옵니다,
		int listcount = boarddao.getListCount();

		// 리스트를 받아옵니다.
		boardlist = boarddao.getBoardList(page, limit);

		/*
		 * 총페이지수 = (DB에 저장된 총 리스트의 수 + 한페이지에서 보여주는 리스트의 수 -1)/한페이지에서 보여준는 리스트의 수
		 * 
		 * 예를 들어 한페이지에서 보여주는 리스트의 수가10 개인 경우 예1) DB에 저장된 총리스트수가 0이면 총페이지수 0페이지 예2) DB에
		 * 저장된 총리스트수가 1~10이면 총페이지수 2페이지 예3) DB에 저장된 총리스트수가 11~20이면 총페이지수 3페이지 예4) DB에
		 * 저장된 총리스트수가 21~30이면 총페이지수 4페이지
		 * 
		 */
		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총페이지수 =" + maxpage);

		/*
		 * startpage : 현재 페이지 그룹에서 맨 처음에 표시 될 페이지 수를 의미합니다. ([1], [11], [21] 등...)보여줄
		 * 페이지가 30개일 경우 [1][2][3].....[30]까지 다 표기하기가 너무 많아 한페이지에는 10페이지까지 이동할수있게표시 페이지는
		 * startpage에 마지막 페이지는 endpage에 구합니다. 예로 1~10페이지 내용을 나타낼떄는 페이지 그룹은
		 * [1][2][3]..[10]로 표시되고 11~20페이지의 내용을 나타낼 때느페이지그룹은 [11][12][13]..[20]로 표시되됩니다.
		 * 
		 */
		int startpage = ((page - 1) / 10) * 10 + 1;

		System.out.println("현재페이지에 보여줄 시작 페이지수 =" + startpage);

		// endpage 현재페이지 그룹에서 보여줄 마지막 페이지수 [10], [20], [30]

		int endpage = startpage + 10 - 1;

		System.out.println("현재페이지에 보여줄 마지막 페이지수 =" + endpage);

		/*
		 * 마지막 그룹의 마지막 페이지 값은 최대 페이지 값입니다. 예로 마지막 페이지그룹이21~30인경우 시작페이지 21 와 마지막페이지 30이지만
		 * 최대페이지가 25라면 21~25까지 표시
		 */

		if (endpage > maxpage) endpage = maxpage;

			request.setAttribute("page", page);
			request.setAttribute("maxpage", maxpage);

			// 현재 페이지에 표시할 첫 페이지수
			request.setAttribute("startpage", startpage);

			// 현재 페이지에 표시 할 끝 페이지수
			request.setAttribute("endpage", endpage);
			request.setAttribute("listcount", listcount);

			// 해당페이지의 글 목록을 갖고있는리스트
			request.setAttribute("boardlist", boardlist);

			request.setAttribute("limit", limit);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);

			// 글목록 페이지로 이동하기 위해 경로를 설정합니다.
			forward.setPath("weview/mypage_board_written.jsp");
			return forward;// BoardFrontController.java로 리턴됩니다.

		
	}
}
