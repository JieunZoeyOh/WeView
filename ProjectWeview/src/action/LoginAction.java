package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
	
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
    	String email = "";
    	Cookie cookies[] = request.getCookies();
    	if(cookies != null){
    		for(int i=0; i<cookies.length; i++){
    			if(cookies[i].getName().equals("email")){
    				email = cookies[i].getValue();
    			}
    		}
    	}
    	request.setAttribute("email", email);
    	ActionForward forward = new ActionForward();
    	forward.setRedirect(false);
    	forward.setPath("weview/loginForm.jsp");
    	return forward;
	}

}