package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.CategoryDAO;
import dto.BoardDTO;
import dto.CategoryDTO;

public class WeivewSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		CategoryDAO categorydao = new CategoryDAO();
		
		List<CategoryDTO> categorylist = categorydao.getCategory();
		categorylist= categorydao.getCategory();
		request.setAttribute("category", categorylist);
		
		String search = request.getParameter("search");
		
		int page=1;
		int limit=15;
		
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = "+page);
		
		int listcount = dao.searchListCount(search);
		List<BoardDTO> list = dao.seach(page, limit, search);
		
		List<BoardDTO> bestlist = dao.getBestList();
		request.setAttribute("bestlist", bestlist);
		
		int maxpage = (listcount + limit -1)/limit;
		System.out.println("총 페이지수 = "+maxpage);
		
		int startpage = ((page-1)/10) * 10 +1;
		int endpage = startpage +10 -1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = "+endpage);
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = "+startpage);
		
		if(endpage>maxpage) endpage=maxpage;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		
		System.out.println(list.size());
		
    	forward.setRedirect(false);
    	forward.setPath("weview/main.jsp");
    	return forward;
	}
}
