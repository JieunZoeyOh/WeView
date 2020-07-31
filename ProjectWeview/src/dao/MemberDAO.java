package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDTO;

public class MemberDAO {
	DataSource ds = null;

	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public boolean isCommWriter(String pwd) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = "select * from member where email= 'admin'";

		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (pwd.equals(rs.getString("pwd"))) {
					result = true;
				}
			}
		} catch (SQLException ex) {
			System.out.println("isCommWriter()  에러:" + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return result;
	}

	public boolean memberDelete(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String delete_sql = "delete from member where email = ?";
		boolean result_check = false;
		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(delete_sql);
			pstmt.setString(1, email);

			System.out.println(delete_sql);
			System.out.println(email);

			pstmt.executeUpdate();
			// 삭제가 안되는 경우 false를 반환합니다.
			result_check = true;

		} catch (Exception ex) {
			System.out.println("boardDelete()에러: " + ex);
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

	public List<MemberDTO> getList() {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<MemberDTO> list = new ArrayList<MemberDTO>();

		try {

			con = ds.getConnection();
			String sql = "select email,pwd,jumin,gender,phone,name,block from member";
			System.out.println(sql);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MemberDTO m = new MemberDTO();

				m.setEmail(rs.getString(1));
				m.setPwd(rs.getString(2));
				m.setJumin(rs.getString(3));
				m.setGender(rs.getString(4));
				m.setPhone(rs.getString(5));
				m.setName(rs.getString(6));
				m.setBlock(rs.getInt(7));
				list.add(m);

			}
		} catch (Exception e) {
			System.out.println("getList() 에러 : " + e);
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

	public int isId(String email, String pass) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int result = 0;
	      try {
	         conn = ds.getConnection();

	         String checkid_sql = "select email, pwd from member where email = ? and block=0";
	         pstmt = conn.prepareStatement(checkid_sql);
	         pstmt.setString(1, email);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            if (rs.getString("pwd").equals(pass)) {
	               result = 1; // 아이디와 비밀번호가 일치하는 경우
	            } else {
	               result = 0; // 비밀번호가 일치하지 않는 경우
	            }
	         } else {
	            result = -1; // 아이디가 존재하지 않습니다.
	         }
	      } catch (Exception e) {
	         System.out.println("isId(String, String)에러");
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
	
	public int isId(String email) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null ;
		int result =-1;//DB에 해당 ID가 없습니다. 기본값 
		
		
		try {	
			
			con = ds.getConnection(); //ds == datasource 클래스 데이터 풀 부러오기
			System.out.println("getConnection");
			
			String sql="select email from member where email = ? ";
			System.out.println(email);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email); //현재 받아온 ID 입력해서 확인
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=0;
			}
			
			
		} catch (SQLException se) {
			System.out.println("MemberDAO.isID(id) " + se.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			};
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			};
			
			
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			};
			
			
		};
		return result;
	}
	
	public boolean insert(MemberDTO m) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		boolean rs = false;
		int result =-1;
		
		try {	
			
			conn = ds.getConnection(); 
			System.out.println("getConnection:insert()");
			
			String insertSql="insert into member values (?,?,?,?,'남',?,0,?)";
			
			pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, m.getEmail()); 
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getJumin());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getName());
			pstmt.setString(6, m.getImage());
			result = pstmt.executeUpdate();// 쿼리 실행되면 1반환
			
			if(result != -1) rs=true;
			
			
			//primary key 제약조건 위반했을 경우 발생하는 에러
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			result =-1;
			System.out.println("MemberDAO.insert() 멤버 아이디 중복 에러입니다." + e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			};
			
			
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			};
			
			
		};
		return rs;
	}
	
}
