package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.NoticeDTO;

public class NoticeDAO {
	DataSource ds = null;

	public NoticeDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	
	//글의 갯수 구하기
		public int getListCount() {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = 0;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement("select count(*) from notice");
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					x= rs.getInt(1);
				}
			}catch(Exception ex) {
				System.out.println("NoticegetListCount() 에러: " + ex);
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
		
		//글 목록 보기
		public List<NoticeDTO> getNoticeList(int page, int limit){
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String notice_list_sql =
					"select * from "
					+ "(select rownum rnum, no, title, content, "
					+ "regidate, image1, image2, image3 "
					+ "from (select * from notice "
					+ "		order by no desc) "	
					+ " ) "
					+ "where rnum>=? and rnum<=?";
			
			List<NoticeDTO> list = new ArrayList<NoticeDTO>();
			
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit -1;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(notice_list_sql);
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, endrow);
				rs=pstmt.executeQuery();
				
				//DB에서 가져온 데이터를 VO객체에 담습니다.
				while(rs.next()) {
					NoticeDTO notice = new NoticeDTO();
					notice.setNo(rs.getInt("no"));
					notice.setTitle(rs.getString("title"));
					notice.setContent(rs.getString("content"));
					notice.setRegidate(rs.getString("regidate"));
					notice.setImage1(rs.getString("image1"));
					notice.setImage2(rs.getString("image2"));
					notice.setImage3(rs.getString("image3"));

					list.add(notice);
				}
			}catch(Exception ex) {
				System.out.println("getNoticeList() 에러: " + ex);
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

		public boolean noticeInsert(NoticeDTO notice) {
			Connection con = null;
			PreparedStatement pstmt = null, pstmt2 = null;
			ResultSet rs = null;
			int no = 1;
			String sql = "";
			boolean result = false;
			try {
				con = ds.getConnection();
				
				//board 테이블의 board_num 필드의 최대값을 구해와서 글을
				//등록할 때 글 번호를 순차적으로 지정하기 위함입니다.
				String max_sql = "select max(no) from notice";
				pstmt = con.prepareStatement(max_sql);
				rs=pstmt.executeQuery();		
				while(rs.next()) {
					System.out.println("rs.getInt(1)=" +rs.getInt(1));
					no = rs.getInt(1) + 1; //최대값보다 1만큼 큰 값을 지정합니다.
				}		
				sql = "insert into notice "
						+ "(no, title, content, regidate, "
						+ "image1, image2, image3 ) "
						+ "values (?,?,?,sysdate,?,?,?)";		
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, no);
				pstmt2.setString(2, notice.getTitle());
				pstmt2.setString(3, notice.getContent());
				pstmt2.setString(4, notice.getImage1());
				pstmt2.setString(5, notice.getImage2());
				pstmt2.setString(6, notice.getImage3());
			
				int r = pstmt2.executeUpdate();
				
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
				if(r==1) result=true;
				
			}catch(Exception ex) {
				System.out.println("noticeInsert() 에러: " + ex);
			}finally {
				if(rs != null)
					try {
						rs.close();
					}catch(SQLException ex) {}
				if(pstmt != null)
					try {
						pstmt.close();
					}catch(SQLException ex) {}
				if(pstmt2 != null)
					try {
						pstmt2.close();
					}catch(SQLException ex) {}
				if(con != null)
					try {
						con.close();
					}catch(SQLException ex) {}
			}			
			return result;
		}

		public NoticeDTO getDetail(int no) {
			NoticeDTO notice = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "";
			try {
				con = ds.getConnection();
				sql = "select * from notice where no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs=pstmt.executeQuery();
			
				while(rs.next()) {
					if(notice==null)
						notice = new NoticeDTO();
					
					notice.setNo(rs.getInt("no"));
					notice.setTitle(rs.getString("title"));
					notice.setContent(rs.getString("content"));
					notice.setRegidate(rs.getString("regidate"));
					notice.setImage1(rs.getString("image1"));
					notice.setImage2(rs.getString("image2"));
					notice.setImage3(rs.getString("image3"));
				}
			}catch(Exception ex) {
				System.out.println("getDetail() 에러: " +ex);
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
			return notice;
		}

		public boolean noticeModify(NoticeDTO notice) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			String sql = "update notice "
						+ "set title=?, content=?, "
						+ "image1=?, image2=?, image3=?"
						+ "where no = ? ";
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, notice.getTitle());
				pstmt.setString(2, notice.getContent());
				pstmt.setString(3, notice.getImage1());
				pstmt.setString(4, notice.getImage2());
				pstmt.setString(5, notice.getImage3());
				pstmt.setInt(6, notice.getNo());
			
				
				int result = pstmt.executeUpdate();
				if(result==1) {
					System.out.println("성공 업데이트");
					return true;
				}
				
			}catch(SQLException ex) {
				System.out.println("noticeModify() 에러: " +ex);
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
			
			return false;
		}

		public boolean boardDelete(int no) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "delete from notice "
					+ "where no = ?";
			boolean result_check = false;
			
			try {
				con=ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,  no);
				int result = pstmt.executeUpdate();
				if(result==1) 
					result_check = true;

			}catch(SQLException ex) {
				System.out.println("boardDelete() 에러: " +ex);
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