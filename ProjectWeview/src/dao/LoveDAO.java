package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.LoveDTO;

public class LoveDAO {
	DataSource ds = null;

	public LoveDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	
	public boolean emailcheck(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from love where member_email=?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) result=true;
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
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	public boolean insert(LoveDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int num = 0;
		boolean result = false;
		try {
			conn = ds.getConnection();

			String max_sql = "select max(no) from love";
			pstmt = conn.prepareStatement(max_sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1; // 최대값보다 1만큰 큰 값을 지정
			}
			
			pstmt2 = conn.prepareStatement("insert into love values(?,?,?)");
			pstmt2.setInt(1, num);
			pstmt2.setInt(3, dto.getBoard_no());
			pstmt2.setString(2, dto.getMember_email());
			
			int r = pstmt2.executeUpdate();

			System.out.println("데이터 삽입이 모두 완료되었습니다.");
			if (r == 1) result = true;
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
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	public boolean delete(int bno, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = -1;
		boolean result = false;
		try {
			conn=ds.getConnection();
			pstmt = conn.prepareStatement("delete from love where member_email=? and board_no=?");
			pstmt.setString(1, email);
			pstmt.setInt(2, bno);
			rs = pstmt.executeUpdate();
			if(rs != -1) result = true;
		} catch (Exception e) {
			System.out.println("insert() 에러 : " + e);
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
	
	public boolean lovecheck(int bno, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn=ds.getConnection();
			pstmt = conn.prepareStatement("select * from love where member_email=? and board_no=?");
			pstmt.setString(1, email);
			pstmt.setInt(2, bno);
			rs = pstmt.executeQuery();
			if(rs.next()) result = true;
		} catch (Exception e) {
			System.out.println("lovecheck() 에러 : " + e);
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

	public int getLoveListCount(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from love where member_email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("getLoveListCount() 에러: " + ex);
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

	public List<LoveDTO> getLoveList(String email, int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String love_list_sql =
				"select * from "
				+ "(select rownum rnum, c.* "
				+ "from (select love.no, love.board_no, board.title, "
				+ "		board.productName1, board.productName2, board.productName3, "
				+ "     board.like1, board.like2, board.like3 "
				+ "     from love, board "
				+ "		where love.member_email = ? "
				+ "     and love.board_no = board.no "
				+ "		order by no desc) c "	
				+ " ) "
				+ "where rnum>=? and rnum<=? ";
		List<LoveDTO> list = new ArrayList<LoveDTO>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(love_list_sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				LoveDTO love = new LoveDTO();
				love.setNo(rs.getInt("no"));
				love.setBoard_no(rs.getInt("board_no"));
				love.setTitle(rs.getString("title"));
				String productName1= rs.getString("productName1");
				String productName2=rs.getString("productName2");
				String productName3=rs.getString("productName3");
				int like1=rs.getInt("like1");
				int like2=rs.getInt("like2");
				int like3=rs.getInt("like3");
				int max = like1;
				if(max < like2) max=like2;
				if(max < like3) max=like3;
				String best = productName1;
				if(max==like2) best=productName2;
				if(max==like3) best=productName3;
				love.setBest(best);
				list.add(love);
			}
		}catch(Exception ex) {
			System.out.println("getLoveList() 에러: " + ex);
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

	public boolean myLoveDelete(String email, int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String mylove_delete_sql =
				"delete from love "
				+ "where member_email = ? "
				+ "and no = ? ";
		boolean result_check = false;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(mylove_delete_sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, no);
			int result = pstmt.executeUpdate();
			if(result==1) 
				result_check = true;
		}catch(SQLException ex) {
			System.out.println("myLoveDelete() 에러: " +ex);
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
