package action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportBoardDAO;
import dto.ReportBoardDTO;

public class AdminReportListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReportBoardDAO reportdao = new ReportBoardDAO();
		List<ReportBoardDTO> reportlist = new ArrayList<ReportBoardDTO>();
		
		int page = 1;	//보여줄 page
		int limit = 10;	//한 페이지에 보여줄 게시판 목록의 수
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);

		//총 리스트 수를 받아옵니다. group by 한 rownum의 수
		int listcount = reportdao.getListCount();
		System.out.println("총 리스트 수 : "+listcount);
		//리스트를 받아옵니다.
		reportlist = reportdao.getReportList(page, limit);
		System.out.println("리스트 : "+reportlist.size());
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
		request.setAttribute("reportlist", reportlist);
	
		request.setAttribute("limit", limit);			
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		//글 목록 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("admin/adminReport.jsp");
		return forward;
		
	}
}
