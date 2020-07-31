package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import dao.NoticeDAO;
import dto.NoticeDTO;

public class AdminNoticelistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		NoticeDAO noticedao = new NoticeDAO();
		List<NoticeDTO> noticelist = new ArrayList<NoticeDTO>();
		
		//濡쒓렇�씤 �꽦怨듭떆 �뙆�씪誘명꽣 page媛� �뾾�뼱�슂. 洹몃옒�꽌 珥덇린媛믪씠 �븘�슂.
		int page = 1;	//蹂댁뿬以� page
		int limit = 10;	//�븳 �럹�씠吏��뿉 蹂댁뿬以� 寃뚯떆�뙋 紐⑸줉�쓽 �닔
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�꽆�뼱�삩 �럹�씠吏� = " + page);
		
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("�꽆�뼱�삩 limit = " + limit);
		
		//珥� 由ъ뒪�듃 �닔瑜� 諛쏆븘�샃�땲�떎.
		int listcount = noticedao.getListCount();
				
		//由ъ뒪�듃瑜� 諛쏆븘�샃�땲�떎.
		noticelist = noticedao.getNoticeList(page, limit);
		
		int maxpage = (listcount + limit -1 ) / limit;
		System.out.println("珥� �럹�씠吏� �닔 = " + maxpage);
		
		int startpage = ((page -1) / 10) * 10 + 1;
		System.out.println("�쁽�옱 �럹�씠吏��뿉 蹂댁뿬以� �떆�옉 �럹�씠吏� �닔 =" + startpage);
		
		//endpage: �쁽�옱 �럹�씠吏� 洹몃９�뿉�꽌 蹂댁뿬以� 留덉�留� �럹�씠吏� �닔([10],[20],[30] �벑...)
		int endpage=startpage + 10 - 1 ;
		System.out.println("�쁽�옱 �럹�씠吏��뿉 蹂댁뿬以� 留덉�留� �럹�씠吏� �닔 = " + endpage);
		
		if(endpage > maxpage) {
			endpage = maxpage;
		}

		


			request.setAttribute("page", page); //�쁽�옱 �럹�씠吏� �닔
			request.setAttribute("maxpage", maxpage); //理쒕� �럹�씠吏� �닔
			
			//�쁽�옱 �럹�씠吏��뿉 �몴�떆�븷 泥� �럹�씠吏� �닔
			request.setAttribute("startpage", startpage);
			
			//�쁽�옱 �럹�씠吏��뿉 �몴�떆�븷 �걹 �럹�씠吏� �닔
			request.setAttribute("endpage", endpage);
			
			request.setAttribute("listcount", listcount); //珥� 湲��쓽 �닔
			
			//�빐�떦 �럹�씠吏��쓽 湲� 紐⑸줉�쓣 媛뽮퀬 �엳�뒗 由ъ뒪�듃
			request.setAttribute("noticelist", noticelist);
			
			request.setAttribute("limit", limit);			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			
			//湲� 紐⑸줉 �럹�씠吏�濡� �씠�룞�븯湲� �쐞�빐 寃쎈줈瑜� �꽕�젙�빀�땲�떎.
			forward.setPath("admin/adminNotice.jsp");
			return forward; //BoardFrontController.java濡� 由ы꽩�맗�땲�떎.
			

	}
}
