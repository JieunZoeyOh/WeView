package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ReportBoardDTO;

public class ReportBoardDAO {
	DataSource ds = null;

	public ReportBoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public List<ReportBoardDTO> getcomm() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<ReportBoardDTO> list = new ArrayList<ReportBoardDTO>();
		try {
			con = ds.getConnection();
			String sql = "select no,board_no,member_email,reason_report,report_count,reportdate,comm_no from reportBoard where type=1";
			System.out.println(sql);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ReportBoardDTO m = new ReportBoardDTO();
				m.setNo(rs.getInt(1));
				m.setBoard_no(rs.getInt(2));
				m.setMember_email(rs.getString(3));
				m.setReason_report(rs.getString(4));
				m.setReport_count(rs.getInt(5));
				m.setReportdate(rs.getString(6));
				m.setComm_no(rs.getInt(7));
				list.add(m);

			}
		} catch (Exception e) {
			System.out.println("getcomm() 에러 : " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return list;
	}

	public boolean commDelete(int board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String delete_sql = "delete from reportBoard where comm_no = ? and type = 1 ";
		boolean result_check = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(delete_sql);
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
			// 삭제가 안되는 경우 false를 반환합니다.
			result_check = true;
		} catch (Exception ex) {
			System.out.println("CommDelete()에러: " + ex);
			ex.printStackTrace();
		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result_check;
	}

	public boolean reportboardcheck(String email, int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from reportboard where member_email=? and board_no=? and type=0");
			pstmt.setString(1, email);
			pstmt.setInt(2, bno);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = true;
		} catch (Exception e) {
			System.out.println("reportboardcheck() 에러 : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	public boolean boardInsert(ReportBoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		int num = 0;
		boolean result = false;
		try {
			conn = ds.getConnection();

			String max_sql = "select max(no) from reportboard";
			pstmt = conn.prepareStatement(max_sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1; // 최대값보다 1만큰 큰 값을 지정
			}

			pstmt2 = conn.prepareStatement(
					"insert into reportboard values(?,0,?,?,sysdate,?,(select nvl(max(report_count),0)+1 from reportboard where board_no=? and type=0),0)");
			pstmt2.setInt(1, num);
			pstmt2.setInt(2, dto.getBoard_no());
			pstmt2.setString(3, dto.getMember_email());
			pstmt2.setString(4, dto.getReason_report());
			pstmt2.setInt(5, dto.getBoard_no());

			int r = pstmt2.executeUpdate();

			pstmt3 = conn.prepareStatement(
					"update board "
					+ "set report = (select nvl(max(report_count),0) "
					+ "				from reportBoard where board_no=? and type=0) where no=?");
			pstmt3.setInt(1, dto.getBoard_no());
			pstmt3.setInt(2, dto.getBoard_no());
			r = pstmt3.executeUpdate();

			System.out.println("데이터 삽입이 모두 완료되었습니다.");
			if (r == 1)
				result = true;
		} catch (Exception e) {
			System.out.println("insert() 에러 : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (pstmt2 != null)
					pstmt2.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (pstmt3 != null)
					pstmt3.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	public boolean reportcommcheck(String email, int cno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from reportboard where member_email=? and board_no=? and type=1");
			pstmt.setString(1, email);
			pstmt.setInt(2, cno);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = true;
		} catch (Exception e) {
			System.out.println("reportcommcheck() 에러 : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	public boolean commInsert(ReportBoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		int num = 0;
		boolean result = false;
		try {
			conn = ds.getConnection();

			String max_sql = "select max(no) from reportboard";
			pstmt = conn.prepareStatement(max_sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1; // 최대값보다 1만큰 큰 값을 지정
			}

			pstmt2 = conn.prepareStatement(
					"insert into reportboard values(?,1,?,?,sysdate,?,"
					+ "(select nvl(max(report_count),0)+1 from reportboard where board_no=? and type=1),?)");
			pstmt2.setInt(1, num);
			pstmt2.setInt(2, dto.getBoard_no());
			pstmt2.setString(3, dto.getMember_email());
			pstmt2.setString(4, dto.getReason_report());
			pstmt2.setInt(5, dto.getBoard_no());
			pstmt2.setInt(6, dto.getComm_no());

			int r = pstmt2.executeUpdate();

			pstmt3 = conn.prepareStatement(
					"update comm set report = (select nvl(max(report_count),0) from reportboard where board_no=? and type=1) where no=?");
			pstmt3.setInt(1, dto.getBoard_no());
			pstmt3.setInt(2, dto.getBoard_no());
			r = pstmt3.executeUpdate();

			System.out.println("데이터 삽입이 모두 완료되었습니다.");
			if (r == 1)
				result = true;
		} catch (Exception e) {
			System.out.println("insert() 에러 : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (pstmt2 != null)
					pstmt2.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (pstmt3 != null)
					pstmt3.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	
	public int getListCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			con = ds.getConnection();
			String count_sql = "select count(*) "
					 + "from (select * "
					 		+ "from (select rownum rnum, c.* "
					 			  + "from (select count(*) "
					 			  		+ "from reportBoard "
					 			  		+ "where type = 0 "
					 			  		+ "group by board_no) c "
					 + ") )"; //board_no를 group으로 묶어서 총 그룹 몇개인지 나온다.
			pstmt = con.prepareStatement(count_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("ReportgetListCount() 에러: " + ex);
		}finally {
			try {
				if(rs!=null) rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(con!=null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return x;
	}

	public List<ReportBoardDTO> getReportList(int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql =
				"select * "
				+ "from (select rownum rnum, c.* "
				+ "		from (select * "
				+ "			  from (select board_no, report_count, title, reportBoard.no, reportBoard.member_email, reportdate, reason_report  "
				+ "     			from reportBoard , board "
				+ "					where reportBoard.board_no = board.no "
				+ "					and type=0"
				+ "     			) "
				+ "			  order by board_no desc , report_count desc"	
				+ "     	  ) c "
				+ "		) a , "
				+ "		( "
				+ "		select board_no, max(report_count) "
				+ "		from REPORTBOARD "
				+ "		group by board_no order by board_no desc "
				+ "		) m "
				+ "where a.board_no >= ? and a.board_no < =? and a.board_no = m.board_no";		
		List<ReportBoardDTO> list = new ArrayList<ReportBoardDTO>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit -1;
		int from = getBoardno(startrow);
		int to = getBoardno(endrow);
		System.out.println("from :" +from +"to: "+to);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, to);
			pstmt.setInt(2, from);
			rs=pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rs.next()) {
				ReportBoardDTO report = new ReportBoardDTO();
				report.setBoard_no(rs.getInt("board_no"));
				report.setTitle(rs.getString("title"));
				report.setReport_count(rs.getInt("report_count"));
				report.setNo(rs.getInt("no"));
				report.setMember_email(rs.getString("member_email"));
				report.setReportdate(rs.getString("reportdate"));
				report.setReason_report(rs.getString("reason_report"));
				report.setMax_count(rs.getInt("max(report_count)"));
				list.add(report);
			}
		}catch(Exception ex) {
			System.out.println("getReportList() 에러: "+ex);
			ex.printStackTrace();
		}finally {
			if(rs != null)
				try {
					rs.close();
				}catch(SQLException ex) {}
			if(pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {}
			if(con != null)
				try {
					con.close();
				}catch(SQLException ex) {}
		}
		return list;
	}
	

	private int getBoardno(int row) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			con = ds.getConnection();
			String sql = "select board_no "
					   + "from (select rownum rnum, b.* "
					   + "	    from (select board_no "
					   + "	  		  from reportBoard "
					   + "			   where type = 0 "
					   + "			  group by board_no "
					   + "			  order by board_no desc "
					   + "	  		 ) b "
					   + "	  ) "
					   + "where rnum = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, row);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("getBoardno(int) 에러: " + ex);
		}finally {
			try {
				if(rs!=null) rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(con!=null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return x;
		
	}

	public int getListCount(int board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			con = ds.getConnection();
			String sql = "select count(*) from reportboard where board_no = ? and type=0";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x= rs.getInt(1);
			}
			System.out.println("에이젝스 카운트"+x);
		}catch(Exception ex) {
			System.out.println("getListCount(int) 에러: " + ex);
		}finally {
			try {
				if(rs!=null) rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(con!=null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return x;
	}

	public List<ReportBoardDTO> getReportList(int board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReportBoardDTO> list = new ArrayList<ReportBoardDTO>();
		String sql = "select no, member_email, reportdate, reason_report, board_no "
				+ "from reportBoard "
				+ "where board_no = ? "
				+ "and type = 0 ";
				
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ReportBoardDTO report = new ReportBoardDTO();
				report.setNo(rs.getInt("no"));
				report.setMember_email(rs.getString("member_email"));
				report.setReportdate(rs.getString("reportdate"));
				report.setReason_report(rs.getString("reason_report"));
				report.setBoard_no(rs.getInt("board_no"));
				list.add(report);
			}
		}catch(Exception ex) {
			System.out.println("getBoardList() 에러: " + ex);
			ex.printStackTrace();
		}finally {
			if(rs != null)
				try {
					rs.close();
				}catch(SQLException ex) {}
			if(pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {}
			if(con != null)
				try {
					con.close();
				}catch(SQLException ex) {}
		}
		return list;
	}

	public boolean reportOk(int board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from reportBoard "
				+ "where board_no = ?";
		boolean result_check = false;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  board_no);
			int result = pstmt.executeUpdate();
			if(result>=1) 
				result_check = true;

		}catch(SQLException ex) {
			System.out.println("reportOk() 에러: " +ex);
		}finally {
			if(pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {}
			if(con != null)
				try {
					con.close();
				}catch(SQLException ex) {}
		}
		return result_check;
	}

	public boolean reportDelete(int board_no) {
		Connection con = null;
		PreparedStatement pstmt = null, pstmt2=null;
		String sql1 = "delete from reportBoard where board_no = ?";
		String sql2 = "delete from board "
				+ "where no = ?";
		boolean result_check = false;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1,  board_no);
			int result = pstmt.executeUpdate();
			if(result>=1) {
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, board_no);
				result = pstmt2.executeUpdate();
				if(result==1) {
					result_check = true;
				}
			}

		}catch(SQLException ex) {
			System.out.println("reportDelete() 에러: " +ex);
		}finally {
			if(pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {}
			if(con != null)
				try {
					con.close();
				}catch(SQLException ex) {}
		}
		return result_check;
	}

	public boolean reportMemberBlock(int board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update member " + 
					 "set block = 1 " + 
					 "where email = (select member_email " + 
					 "	   		   from board " + 
					 "			   where no = ? " + 
					 				")";
		boolean result_check = false;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  board_no);
			int result = pstmt.executeUpdate();
			if(result==1) 
				result_check = true;

		}catch(SQLException ex) {
			System.out.println("reportMemberBlock() 에러: " +ex);
		}finally {
			if(pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {}
			if(con != null)
				try {
					con.close();
				}catch(SQLException ex) {}
		}
		return result_check;
	}
}
