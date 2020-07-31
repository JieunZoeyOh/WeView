package action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.NoticeDAO;
import dto.NoticeDTO;

public class AdminModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeDAO noticedao = new NoticeDAO();
		NoticeDTO noticedata = new NoticeDTO();
		ActionForward forward = new ActionForward();
		String realFolder="";
		String saveFolder="noticeupload";
		
		int fileSize = 5*1024*1024;//���ε��� ������ �ִ� ������ �Դϴ�. 5MB
		
		//���� ���� ��θ� �����մϴ�.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder = " + realFolder);
		boolean result = false;
		
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, 
					realFolder, 
					fileSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			int no = Integer.parseInt(multi.getParameter("no"));			
			noticedata.setNo(no);
			noticedata.setTitle(multi.getParameter("title"));
			noticedata.setContent(multi.getParameter("content"));
			noticedata.setRegidate(multi.getParameter("regidate"));
			
			String check1 = multi.getParameter("check1");
			String check2 = multi.getParameter("check2");
			String check3 = multi.getParameter("check3");
			System.out.println("check1 = " + check1);
			System.out.println("check2 = " + check2);
			System.out.println("check3 = " + check3);
			
			if(check1!=null) {	//�������� �״�� ����ϴ� ����Դϴ�.
				noticedata.setImage1(check1);
			}else { //���ε�� ������ �ý��� �� ���ε�� ���� ���ϸ��� ��� �ɴϴ�.
				String filename1 = multi.getFilesystemName("image1");
				noticedata.setImage1(filename1);
			}
			if(check2!=null) {	
				noticedata.setImage2(check2);
			}else {				
				String filename2 = multi.getFilesystemName("image2");
				noticedata.setImage2(filename2);
			}
			if(check3!=null) {
				noticedata.setImage3(check3);
			}else {
				String filename3 = multi.getFilesystemName("image3");
				noticedata.setImage3(filename3);
			}
			//DAO���� ���� �޼��� ȣ���Ͽ� �����մϴ�.
			result = noticedao.noticeModify(noticedata);
			
			//������ ������ ���
			if(result==false) {
				System.out.println("�Խ��� ���� ����");
				forward=new ActionForward();
				forward.setRedirect(false);
				request.setAttribute("message", "�Խ��� ������ ���� �ʾҽ��ϴ�.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			//���� ������ ���
			System.out.println("�Խ��� ���� �Ϸ�");
			
			forward.setRedirect(true);
			//������ �� ������ �����ֱ� ���� �� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
			forward.setPath("AdminNoticeDetailAction.com?no="+noticedata.getNo());
			return forward;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}