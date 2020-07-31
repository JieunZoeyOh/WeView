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

import dto.CategoryDTO;

public class CategoryDAO {
	DataSource ds = null;

	public CategoryDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public List<CategoryDTO> getCategory() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();

		try {

			con = ds.getConnection();
			String sql = "select * from category order by no asc";
			System.out.println(sql);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				CategoryDTO m = new CategoryDTO();
				m.setNo(rs.getInt(1));
				m.setName(rs.getString(2));
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

	public boolean categoryInsert(CategoryDTO cat) {
		Connection con = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		int num = 1;
		boolean result = false;

		try {

			con = ds.getConnection();

			String max_sql = "select max(no) from category";
			pstmt = con.prepareStatement(max_sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("rs.getInt(1)=" + rs.getInt(1));
				num = rs.getInt(1) + 1;
			}

			String insert_sql = "insert into category" + "(no,name)" + "values(?,?)";

			pstmt2 = con.prepareStatement(insert_sql.toString());
			pstmt2.setInt(1, num);
			pstmt2.setString(2, cat.getName());

			int r = pstmt2.executeUpdate();
			System.out.println("데이터 삽입이 모두 완료되었습니다.");
			if (r == 1)
				result = true;

		}

		catch (Exception e) {
			System.out.println("categoryInsert() 에러: " + e);
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();

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

	public boolean isBoardWriter(String pwd) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = "select * from member where email= 'admin'";

		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println(pwd);

			if (rs.next()) {
				if (pwd.equals(rs.getString("pwd"))) {

					result = true;
				}

			}

		} catch (SQLException ex) {
			System.out.println("isBoardWriter() 에러:" + ex);

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

	public boolean categoryDelete(int no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String delete_sql = "delete from category where no = ?";

		boolean result_check = false;
		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(delete_sql);
			pstmt.setInt(1, no);

			System.out.println(delete_sql);
			System.out.println(no);

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

}
