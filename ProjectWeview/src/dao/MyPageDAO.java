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

import dto.BoardDTO;




public class MyPageDAO {

	DataSource ds;

	public MyPageDAO() {

		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

		} catch (Exception ex) {

			System.out.println("DB연결실패 : " + ex);
			return;

		}

	}

	
	
public int getListCount() {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int x = 0;
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement("select count(*) from board");
		rs = pstmt.executeQuery();

		if (rs.next()) {
			x = rs.getInt(1);
		}
	} catch (Exception ex) {
		System.out.println("getListCount() 에러: " + ex);
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

	return x;
}

public List<BoardDTO> getBoardList(int page, int limit) {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// page : 페이지
	// limit : 페이지 당 목록의 수
	// BOARD_RE_REF desc, BOARD_RE_SEQ asc에 의해 정렬한 것을
	// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.

	String board_list_sql = "select rownum, no,category_no,title,views,regidate from board where rownum>=? and rownum<=?";
	System.out.println(board_list_sql);

	List<BoardDTO> list = new ArrayList<BoardDTO>();
	// 한 페이지당 10개씩 목록인 겨웅 1페이지,2페이지,3페이지, 4페이지...
	int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호 (1 11 ...)
	int endrow = startrow + limit - 1; // 읽을 마지막 row 번호 (10 20..)
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(board_list_sql);
		pstmt.setInt(1, startrow);
		pstmt.setInt(2, endrow);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			BoardDTO m = new BoardDTO();
			
			m.setNo(rs.getInt("no"));
			m.setCategory_no(rs.getInt("category_no"));
			m.setTitle(rs.getString("title"));
			m.setViews(rs.getInt("views"));
			m.setRegidate(rs.getString("regidate"));
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
	

}
