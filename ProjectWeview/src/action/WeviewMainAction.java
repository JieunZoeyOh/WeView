package action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.CategoryDAO;
import dto.BoardDTO;
import dto.CategoryDTO;

public class WeviewMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		CategoryDAO categorydao = new CategoryDAO();
		List<BoardDTO> list = null;
		List<CategoryDTO> categorylist = categorydao.getCategory();
		categorylist= categorydao.getCategory();
		request.setAttribute("category", categorylist);
		
		int category=1;
		int page=1;
		int limit=15;
		
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("category")!=null) {
			category = Integer.parseInt(request.getParameter("category"));
		}
		
		System.out.println("넘어온 페이지 = "+page);
		System.out.println("카테고리 = "+category);
		
		list = dao.getList(page, limit);
		int listcount = dao.getListCount();
		
		if(category!=1) {
			list = dao.getList(page, limit, category);
			listcount = dao.getListCount(category);
		}
		
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
