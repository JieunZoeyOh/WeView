package action;



import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.MemberDAO;
import dto.MemberDTO;


public class JoinProcessAction implements Action {
 public JoinProcessAction() {
	 
 }
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDTO dto = new MemberDTO();
		MemberDAO dao =new MemberDAO();
		ActionForward forward = new ActionForward();
		
		String realFolder="";
	      //WebContent아래에 폴더 생성
	      String saveFolder="memberupload";
	      
	      int fileSize=5*1024*1024; //업로드할 파일 최대 사이즈 5MB
	      
	      //실제 저장 경로 지정
	      ServletContext sc =request.getServletContext();
	      realFolder = sc.getRealPath(saveFolder);
	      //realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
	      System.out.println("realFolder = "+realFolder);
	      boolean result = false;
	      try {
	         MultipartRequest multi = null;
	         multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
	         
	         //BoardBean 객체에 글 등록 폼에서 입력받은 정보들을 저장합니다.
	         dto.setEmail(multi.getParameter("email"));
	         dto.setPwd(multi.getParameter("pwd"));
	         dto.setJumin(multi.getParameter("jumin"));
	         dto.setPhone(multi.getParameter("phone"));
	         dto.setName(multi.getParameter("name"));
	         
	         // 업로드된 파일의 시스템 상에 업로드된 실제 파일명을 얻어옵니다.
	         String filename = multi.getFilesystemName("image");
	         dto.setImage(filename);
	         
	         //글 등록 처리를 위해 DAO의 boardInsert()메서드를 호출합니다.
	         //글 등록 폼에서 입력한 정보가 저장되어있는 dto객체를 전달합니다.
	         result=dao.insert(dto);
	         
	         //글 등록에 실패할 경우 false를 반환
	         if(result==false) {
	            System.out.println("개시판 등록 실패");
	            forward.setPath("error/error.jsp");
	            request.setAttribute("message", "게시판 등록 실패입니다.");
	            forward.setRedirect(false);
	            return forward;
	         }
	         System.out.println("게시판 등록 완료");
	         
	         // 글 등록이 완료되면 글 목록을 단순히 보여주기만 할 것이므로 Redirect 여부를 true로 설정
	         forward.setRedirect(true);
	         forward.setPath("login.com"); // 이동할 경로 지정
	         return forward;
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      return null;
	   }

}
