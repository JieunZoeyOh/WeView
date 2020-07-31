package dao;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;

public class BoardDAO {
	DataSource ds = null;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public List<BoardDTO> getBestList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String best_list_sql = "select * " 
							 + "from (select rownum rnum, d.* " 
							 		+ "from ( select c.* " 
							 			   + "from ( select a.*, total " 
							 			   		  + "from board a, " 
							 			   		      + "(select no, sum(like1+like2+like3) total "
							 			   		       + "from board group by no ) b " 
							 			   		  + "where a.no = b.no "
							 			   		  + "and a.regidate >= TO_CHAR(SYSDATE-7,'YYYYMMDD')" 
							 			   		  + "order by total desc , a.regidate desc " + ")c "
							 		       + ")d " 
							 		+ ") " 
							 + "where rnum>=1 and rnum<=6 ";

		List<BoardDTO> list = new ArrayList<BoardDTO>();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(best_list_sql);

			rs = pstmt.executeQuery();

			// DB에서 가져온 데이터를 VO 객체에 담습니다.
			while (rs.next()) {
				BoardDTO bestlist = new BoardDTO();
				bestlist.setNo(rs.getInt("no"));
				bestlist.setTitle(rs.getString("title"));
				bestlist.setContent(rs.getString("content"));
				bestlist.setMember_email(rs.getString("member_email"));
				bestlist.setRegidate(rs.getString("regidate"));
				bestlist.setImage1(rs.getString("image1"));
				bestlist.setImage2(rs.getString("image2"));
				bestlist.setImage3(rs.getString("image3"));
				bestlist.setProductName1(rs.getString("productName1"));
				bestlist.setProductName2(rs.getString("productName2"));
				bestlist.setProductName3(rs.getString("productName3"));
				list.add(bestlist);
			}
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
	

	public List<BoardDTO> getList() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = ds.getConnection();
			String sql = "select no,category_no,title,member_email,report,regidate from board";
			System.out.println(sql);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BoardDTO m = new BoardDTO();
				m.setNo(rs.getInt(1));
				m.setCategory_no(rs.getInt(2));
				m.setTitle(rs.getString(3));
				m.setMember_email(rs.getString(4));
				m.setReport(rs.getInt(5));
				m.setRegidate(rs.getString(6));
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

	public boolean boardDelete(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String delete_sql = "delete from board where no = ?";
		boolean result_check = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(delete_sql);
			pstmt.setInt(1, no);
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

	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board");
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
	
	public int getListCount(int category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board where category_no=?");
			pstmt.setInt(1, category);
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

	public List<BoardDTO> getList(int page, int limit, int category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String member_list_sql = "select * from board where category_no=? order by regidate desc";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		// 한 페이지 당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지 ...
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(member_list_sql);
			pstmt.setInt(1, category);
			rs = pstmt.executeQuery();

			// DB에서 가져온 데이터를 VO 객체에 담습니다.
			while (rs.next()) {
				BoardDTO main = new BoardDTO();
				main.setNo(rs.getInt("no"));
				main.setTitle(rs.getString("title"));
				main.setContent(rs.getString("content"));
				main.setMember_email(rs.getString("member_email"));
				main.setRegidate(rs.getString("regidate"));
				main.setImage1(rs.getString("image1"));
				main.setImage2(rs.getString("image2"));
				main.setImage3(rs.getString("image3"));
				main.setProductName1(rs.getString("productName1"));
				main.setProductName2(rs.getString("productName2"));
				main.setProductName3(rs.getString("productName3"));
				main.setCategory_no(rs.getInt("category_no"));
				list.add(main);
			}
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
	
	public List<BoardDTO> getList(int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String member_list_sql = "select * from (select b.*, rownum rnum " + "from (select * from board "
				+ "order by regidate desc) b) " + "where rnum>=? and rnum <=? order by regidate desc";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		// 한 페이지 당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지 ...
		int startrow = (page - 1) * limit + 1; // 읽기 지삭할 row 번호(1 11 21...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호 (10 20 30...)
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(member_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			// DB에서 가져온 데이터를 VO 객체에 담습니다.
			while (rs.next()) {
				BoardDTO main = new BoardDTO();
				main.setNo(rs.getInt("no"));
				main.setTitle(rs.getString("title"));
				main.setContent(rs.getString("content"));
				main.setMember_email(rs.getString("member_email"));
				main.setRegidate(rs.getString("regidate"));
				main.setImage1(rs.getString("image1"));
				main.setImage2(rs.getString("image2"));
				main.setImage3(rs.getString("image3"));
				main.setProductName1(rs.getString("productName1"));
				main.setProductName2(rs.getString("productName2"));
				main.setProductName3(rs.getString("productName3"));
				list.add(main);
			}
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

	public void setReadCountUpdate(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update board set views=views+1 where no=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("setReadCountUpdate() 에러 : " + e);
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
	}

	public BoardDTO getDetail(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO board = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from board where no = " + no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new BoardDTO();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setCategory_no(rs.getInt("category_no"));
				board.setMember_email(rs.getString("member_email"));
				board.setReport(rs.getInt("report"));
				board.setRegidate(rs.getString("regidate"));
				board.setImage1(rs.getString("image1"));
				board.setImage2(rs.getString("image2"));
				board.setImage3(rs.getString("image3"));
				board.setProductName1(rs.getString("productName1"));
				board.setProductName2(rs.getString("productName2"));
				board.setProductName3(rs.getString("productName3"));
				board.setLike1(rs.getInt("like1"));
				board.setLike2(rs.getInt("like2"));
				board.setLike3(rs.getInt("like3"));
				board.setViews(rs.getInt("views"));
			}
		} catch (Exception e) {
			System.out.println("getDetail() 에러 : " + e);
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
		return board;
	}

	public boolean weviewInsert(BoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int num = 1;
		boolean result = false;
		try {
			conn = ds.getConnection();
			System.out.println("getConnection : insert()");
			String max_sql = "select max(no) from board";
			pstmt = conn.prepareStatement(max_sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("rs.getInt(1)=" + rs.getInt(1));
				num = rs.getInt(1) + 1; // 최대값보다 1만큰 큰 값을 지정
			}
			String checkid_sql = "insert into board values (?,?,?,?,?,?,sysdate,?,?,?,?,?,?,?,?,?,?)";
			pstmt2 = conn.prepareStatement(checkid_sql);
			pstmt2.setInt(1, num);
			pstmt2.setString(2, dto.getTitle());
			pstmt2.setString(3, dto.getContent());
			pstmt2.setInt(4, dto.getCategory_no());
			pstmt2.setString(5, dto.getMember_email());
			pstmt2.setInt(6, 0);
			pstmt2.setString(7, dto.getImage1());
			pstmt2.setString(8, dto.getImage2());
			pstmt2.setString(9, dto.getImage3());
			pstmt2.setString(10, dto.getProductName1());
			pstmt2.setString(11, dto.getProductName2());
			pstmt2.setString(12, dto.getProductName3());
			pstmt2.setInt(13, 0);
			pstmt2.setInt(14, 0);
			pstmt2.setInt(15, 0);
			pstmt2.setInt(16, 0);
			// 상빕 성공시 result는 1
			int r = pstmt2.executeUpdate();

			System.out.println("데이터 삽입이 모두 완료되었습니다.");
			if (r == 1)
				result = true;
			// Primary key 제약 조건 위반할 경우 발생하는 에러
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("boardInsert() 에러 : " + e);
			e.printStackTrace();
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

	public boolean boardModify(BoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		int r = -1;
		try {
			conn = ds.getConnection();
			String sql = "update board set title=?, content=?,"
					+ " category_no=?,image1=?, image2=?, image3=?, productName1=?,"
					+ "productName2=?, productName3=? where no=" + dto.getNo();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getCategory_no());
			pstmt.setString(4, dto.getImage1());
			pstmt.setString(5, dto.getImage2());
			pstmt.setString(6, dto.getImage3());
			pstmt.setString(7, dto.getProductName1());
			pstmt.setString(8, dto.getProductName2());
			pstmt.setString(9, dto.getProductName3());
			r = pstmt.executeUpdate();
			if (r != -1)
				result = true;
		} catch (Exception e) {
			System.out.println("boardModify() 에러 : " + e);
			System.out.println(e.getMessage());
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

	public List<BoardDTO> seach(int page, int limit, String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String member_list_sql = "select * from (select b.*, rownum rnum " + "from (select * from board "
				+ "order by regidate desc) b) " + "where rnum>=? and rnum <=? and title like ? or content like ? "
				+ "or productName1 like ? or productName2 like ? or productName3 like ? order by regidate desc";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		// 한 페이지 당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지 ...
		int startrow = (page - 1) * limit + 1; // 읽기 지삭할 row 번호(1 11 21...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호 (10 20 30...)
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(member_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			pstmt.setString(3, "%" + search + "%");
			pstmt.setString(4, "%" + search + "%");
			pstmt.setString(5, "%" + search + "%");
			pstmt.setString(6, "%" + search + "%");
			pstmt.setString(7, "%" + search + "%");
			rs = pstmt.executeQuery();

			// DB에서 가져온 데이터를 VO 객체에 담습니다.
			while (rs.next()) {
				BoardDTO main = new BoardDTO();
				main.setNo(rs.getInt("no"));
				main.setTitle(rs.getString("title"));
				main.setContent(rs.getString("content"));
				main.setMember_email(rs.getString("member_email"));
				main.setRegidate(rs.getString("regidate"));
				main.setImage1(rs.getString("image1"));
				main.setImage2(rs.getString("image2"));
				main.setImage3(rs.getString("image3"));
				main.setProductName1(rs.getString("productName1"));
				main.setProductName2(rs.getString("productName2"));
				main.setProductName3(rs.getString("productName3"));
				list.add(main);
			}
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

	public int searchListCount(String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board where title like ? or content like ? "
					+ "or productName1 like ? or productName2 like ? or productName3 like ?");
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			pstmt.setString(4, "%" + search + "%");
			pstmt.setString(5, "%" + search + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("searchListCount() 에러 : " + e);
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

	
	
	public int getWrittenListCount(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from board where member_email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("getWrittenListCount() 에러: " + ex);
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

	public List<BoardDTO> getWrittenList(String email, int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String written_list_sql =
				"select * from "
				+ "(select rownum rnum, no, title, regidate, "
				+ " productName1, productName2, productName3, "
				+ "like1, like2, like3 "
				+ "from (select * from board "
				+ "		where member_email = ? "
				+ "		order by no desc) "	
				+ " ) "
				+ "where rnum>=? and rnum<=? ";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(written_list_sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			rs=pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setRegidate(rs.getString("regidate"));
				String productName1= rs.getString("productName1");
				String productName2=rs.getString("productName2");
				String productName3=rs.getString("productName3");
				int like1=rs.getInt("like1"); //가장 득표수가 많은 제품명 best로 구하기
				int like2=rs.getInt("like2");
				int like3=rs.getInt("like3");
				int max = like1;
				if(max < like2) max=like2;
				if(max < like3) max=like3;
				String best = productName1;
				if(max==like2) best=productName2;
				if(max==like3) best=productName3;
				board.setBest(best);	
				list.add(board);
			}
		}catch(Exception ex) {
			System.out.println("getWrittenList() 에러: " + ex);
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

	public boolean mypostDelete(String email, int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String mypost_delete_sql =
				"delete from board "
				+ "where member_email = ? "
				+ "and no = ? ";
		boolean result_check = false;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(mypost_delete_sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, no);
			int result = pstmt.executeUpdate();
			if(result==1) 
				result_check = true;
		}catch(SQLException ex) {
			System.out.println("mypostDelete() 에러: " +ex);
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