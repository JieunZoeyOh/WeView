package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.CommDTO;

public class CommDAO {
	DataSource ds = null;

	public CommDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public boolean commInsert(CommDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int num = 1;
		boolean result = false;
		try {
			conn = ds.getConnection();

			String max_sql = "select max(no) from comm";
			pstmt = conn.prepareStatement(max_sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1; // 최대값보다 1만큰 큰 값을 지정
			}
			
			pstmt2 = conn.prepareStatement("insert into comm values(?,?,?,?,0,sysdate,0,0,(select image from member where email=?))");
			pstmt2.setInt(1, num);
			pstmt2.setInt(2, dto.getBoard_no());
			pstmt2.setString(3, dto.getContent());
			pstmt2.setString(4, dto.getMember_email());
			pstmt2.setString(5, dto.getMember_email());
			
			int r = pstmt2.executeUpdate();

			System.out.println("데이터 삽입이 모두 완료되었습니다.");
			if (r == 1) result = true;
		} catch (Exception e) {
			System.out.println("commInsert() 에러 : " + e);
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

	public List<CommDTO> getlist(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommDTO> list = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from comm where board_no=?");
			pstmt.setInt(1, no);
			list = new ArrayList<CommDTO>();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommDTO comm = new CommDTO();
				comm.setNo(rs.getInt("no"));
				comm.setBoard_no(rs.getInt("board_no"));
				comm.setContent(rs.getString("content"));
				comm.setMember_email(rs.getString("member_email"));
				comm.setReport(rs.getInt("report"));
				comm.setRegidate(rs.getString("regidate"));
				comm.setLove(rs.getInt("love"));
				comm.setHate(rs.getInt("hate"));
				comm.setImage(rs.getString("image"));
				list.add(comm);
			}
			return list;
		} catch (Exception e) {
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
		return list;
	}

	public int getListCount(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from comm where board_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCount() 에러 : " + e);
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
		return x;
	}

	public int Delete(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("delete from comm where no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeUpdate();
		}catch (SQLException ex) {
			System.out.println("commDelete() 에러: " + ex);
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return rs;
	}
}
