package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.VoteDAO;
import dto.BoardDTO;
import dto.VoteDTO;

public class WeivewVoteAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
		VoteDAO dao = new VoteDAO();
		VoteDTO dto = new VoteDTO();
		BoardDTO bdto = new BoardDTO();
		BoardDAO bdao = new BoardDAO();
		
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		dto.setBoard_no(b_no);
		String m_email = request.getParameter("m_email");
		dto.setMember_email(m_email);
		String product = request.getParameter("voteporduct");
		System.out.println("선택한 제품: "+product);
		dto.setBoard_no(b_no);
		boolean check = dao.voteCheck(m_email, b_no);
		System.out.println("중복 체크:"+check);
		bdto = bdao.getDetail(b_no);
		dto.setLike1(0);
		dto.setLike2(0);
		dto.setLike3(0);
		if(bdto.getProductName1().equals(product)) dto.setLike1(1);
		if(bdto.getProductName2() != null) if(bdto.getProductName2().equals(product)) dto.setLike2(1);
		if(bdto.getProductName3() != null) if(bdto.getProductName3().equals(product)) dto.setLike3(1);
		
		if(check==false) {
			dao.insert(dto);
			dao.update(b_no);
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("WeviewDetail.com?no="+b_no);
		return forward;
	}
}
