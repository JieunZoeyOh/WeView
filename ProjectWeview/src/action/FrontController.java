package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.com")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		ActionForward forward = null;
		Action action = null;

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		System.out.println("email:" + email);

		if (!command.equals("/login.com") && !command.equals("/loginProcess.com") && !command.equals("/join2.com") && !command.equals("/joinProcess.com")) {
			if (email == null)
				command = "/login.com";
		}

		if (command.equals("/login.com")) {
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/join2.com")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("weview/joinForm.jsp");
    	} else if(command.equals("/joinProcess.com")) {
    		action = new JoinProcessAction();
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	} else if (command.equals("/loginProcess.com")) {
			action = new LoginProcessAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/logout.com")) {
    		action = new LogoutAction();
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	}else if (command.equals("/WeviewMain.com")) {
			action = new WeviewMainAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewDetail.com")) {
			action = new WeviewDatailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewWrite.com")) {
			action = new WeviewWriteView();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewAddAction.com")) {
			action = new WeviewAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewCommAction.com")) {
			action = new WeviewCommAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewCommDelete.com")) {
			action = new WeviewCommDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewModifyView.com")) {
			action = new WeivewModifyViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewModify.com")) {
			action = new WeviewModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewDelete.com")) {
			action = new WeviewDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewVote.com")) {
			action = new WeivewVoteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewLove.com")) {
			action = new WeviewLoveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewReportBoard.com")) {
			action = new WeviewReportBoardAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewReportComm.com")) {
			action = new WeviewReportCommAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/WeviewSearch.com")) {
			action = new WeivewSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MyPage.com")) {// 관리자 회원관리
			action = new MyPageAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/AdminMember.com")) {// 관리자 회원관리
			action = new MemberAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/AdminMemberDelete.com")) {// 관리자 회원관리
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (command.equals("/AdminBoard.com")) {// 관리자 게시물관리
			action = new BoardAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (command.equals("/AdminBoardDeleteAction.com")) {// 관리자 게시물 삭제
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (command.equals("/AdminComm.com")) {// 관리자 댓글관리
			action = new commAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/AdminDeleteComm.com")) {// 관리자 댓글삭제
			action = new commDeleteAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/Admincategory.com")) {// 관리자 카테고리관리
			action = new categoryAction();
			try {
				forward = action.execute(request, response);

         }catch(Exception e) {
            e.printStackTrace();
         }
         
   }else if(command.equals("/AdminCategoryAddAction.com")) {//관리자 카테고리추가
       action = new categoryAddAction();
       try {
          forward=action.execute(request, response);
     
       }catch(Exception e) {
          e.printStackTrace();
       }
       
 }
   else if(command.equals("/AdminCategoryDeleteAction.com")) {//관리자 카테고리삭제
       action = new categoryDeleteAction();
       try {
          forward=action.execute(request, response);
     
       }catch(Exception e) {
          e.printStackTrace();
       }
       
 } else if(command.equals("/AdminNoticeList.com")) {
    		action = new AdminNoticelistAction();
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(command.equals("/AdminNoticeDetailAction.com")) {
    		action = new AdminNoticeDetailAction();
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(command.equals("/AdminNoticeWrite.com")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("admin/adminNoticeWrite.jsp");
    	} else if(command.equals("/AdminNoticeAddAction.com")) {
    		action = new AdminNoticeAddAction();
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(command.equals("/AdminNoticeFileDown.com")) {
    		action = new AdminFileDownAction();
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(command.equals("/AdminModifyView.com")) {
    		action = new AdminModifyView();
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(command.equals("/AdminModifyAction.com")) {
    		action = new AdminModifyAction();
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(command.equals("/AdminNoticeDeleteAction.com")) {
    		action = new AdminDeleteAction();
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("/AdminReport.com")) {
     		action = new AdminReportListAction();
     		try {
     			forward = action.execute(request, response);
     		}catch (Exception e) {
 				e.printStackTrace();
 			}
     	} else if(command.equals("/AdminReportOk.com")) {
     		action = new AdminReportOkAction();
     		try {
     			forward = action.execute(request, response);
     		}catch (Exception e) {
 				e.printStackTrace();
 			}
     	} else if(command.equals("/AdminReportDelete.com")) {
     		action = new AdminReportDeleteAction();
     		try {
     			forward = action.execute(request, response);
     		}catch (Exception e) {
 				e.printStackTrace();
 			}
     	} else if(command.equals("/AdminReportBlock.com")) {
     		action = new AdminReportBlockAction();
     		try {
     			forward = action.execute(request, response);
     		}catch (Exception e) {
 				e.printStackTrace();
 			}
     	} else if(command.equals("/WeviewNotice.com")) {
      		action = new WeviewNoticeAction();
      		try {
      			forward = action.execute(request, response);
      		}catch (Exception e) {
  				e.printStackTrace();
  			}
      	}else if(command.equals("/WevieNoticeDetailAction.com")) {
       		action = new WeviewNoticeDetailAction();
       		try {
       			forward = action.execute(request, response);
       		}catch (Exception e) {
   				e.printStackTrace();
   			}
       	}else if(command.equals("/WeviewContact.com")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("weview/service_contact.jsp");
       	} else if(command.equals("/WeviewMypageWritten.com")) {
       		action = new WeviewMypageWrtittenAction();
       		try {
       			forward = action.execute(request, response);
       		}catch (Exception e) {
   				e.printStackTrace();
   			}
       	} else if(command.equals("/WeviewWrittenDelete.com")) {
       		action = new WeviewMypageWrtittenDeleteAction();
       		try {
       			forward = action.execute(request, response);
       		}catch (Exception e) {
   				e.printStackTrace();
   			}
       	} else if(command.equals("/WeviewMypageLove.com")) {
       		action = new WeviewMypageLoveListAction();
       		try {
       			forward = action.execute(request, response);
       		}catch (Exception e) {
   				e.printStackTrace();
   			}
       	} else if(command.equals("/WeviewMypageLoveDelete.com")) {
       		action = new WeviewMypageLoveDeleteAction();
       		try {
       			forward = action.execute(request, response);
       		}catch (Exception e) {
   				e.printStackTrace();
   			}
       	}    

		
		

		if (forward != null) {
			if (forward.isRedirect()) { // 리다리렉트 됩니다.
				response.sendRedirect(forward.getPath());
			} else { // 포워딩 됩니다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}
}
