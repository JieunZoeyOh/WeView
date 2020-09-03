package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;
import dto.MemberDTO;
import dto.VoteDTO;

public class VoteDAO {
	DataSource ds = null;

	public VoteDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	
	public boolean voteCheck(String email, int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from vote where member_email=? and board_no=?");
			pstmt.setString(1, email);
			pstmt.setInt(2, no);
			rs = pstmt.executeQuery();
			if(rs.next()) result=true;
		} catch (Exception e) {
			System.out.println("getList(int no) 에러 : " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
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
		} return result;
	}
	public ArrayList<VoteDTO> getList(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<VoteDTO> list = new ArrayList<VoteDTO>();

		try {

			con = ds.getConnection();
			String sql = "select * from vote left join member on member_email=member.email where board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				VoteDTO v = new VoteDTO();
				MemberDTO m = new MemberDTO(); 
				v.setNo(rs.getInt(1));
				v.setBoard_no(rs.getInt(2));
				v.setMember_email(rs.getString(3));
				v.setLike1(rs.getInt(4));
				v.setLike2(rs.getInt(5));
				v.setLike3(rs.getInt(6));
				m.setJumin(rs.getString(9));
				m.setGender(rs.getString(11));
				v.setDtoMember(m);
				list.add(v);

			}
		} catch (Exception e) {
			System.out.println("getList(int no) 에러 : " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
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
		return list;
	}
	
	public VoteDTO getAllChart(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VoteDTO v = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select ROUND(SUM(like1)), ROUND(SUM(like2)), ROUND(SUM(like3)) from vote where board_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				v = new VoteDTO();
				v.setLike1(rs.getInt(1));
				v.setLike2(rs.getInt(2));
				v.setLike3(rs.getInt(3));
			}
		} catch (Exception e) {
			System.out.println("getAllChart() 에러 : " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
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
		return v;
	}

	public int insert(VoteDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		int num=0;
		int result = 0;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement("select max(no) from vote");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("rs.getInt(1)=" + rs.getInt(1));
				num = rs.getInt(1) + 1;
			}
			
			pstmt2 = con.prepareStatement("insert into vote values (?,?,?,?,?,?)");
			pstmt2.setInt(1, num);
			pstmt2.setInt(2, dto.getBoard_no());
			pstmt2.setString(3, dto.getMember_email());
			pstmt2.setInt(4, dto.getLike1());
			pstmt2.setInt(5, dto.getLike2());
			pstmt2.setInt(6, dto.getLike3());
			
			result = pstmt2.executeUpdate();
		} catch (Exception e) {
			System.out.println("voteInsert() 에러: " + e);
		} finally {

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
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	public int update(int no) {
		Connection con = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		int result = 0;
		BoardDTO v = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select ROUND(SUM(like1)), ROUND(SUM(like2)), ROUND(SUM(like3)) from vote where board_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				v = new BoardDTO();
				v.setLike1(rs.getInt(1));
				v.setLike2(rs.getInt(2));
				v.setLike3(rs.getInt(3));
			}
			
			pstmt2 = con.prepareStatement("update board set like1=?, like2=?, like3=? where no = ?");
			pstmt2.setInt(1, v.getLike1());
			pstmt2.setInt(2, v.getLike2());
			pstmt2.setInt(3, v.getLike3());
			pstmt2.setInt(4, no);
			result = pstmt2.executeUpdate();
		} catch (Exception e) {
			System.out.println("voteupdate() 에러: " + e);
		} finally {
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
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}	
}
