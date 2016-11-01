package net.ToDo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ToDoDAO {

	private Connection getConnection() throws Exception {

		Connection con = null;
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/jspdb");
		con = ds.getConnection();
		return con;
	}

	public void insertToDoList(ToDoBean tb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int num = 0;
		try {
			// 1,2 디비연결 메서드호출
			con = getConnection();
			// num 구하기
			sql = "select max(num) from do_list";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 첫번째 행이동
				// 데이터 있으면 true
				num = rs.getInt(1) + 1; // 최근글번호(최대값num)+1
			} else {
				// 데이터 없으면 false
				num = 1; // 글번호 1번부터 시작
			}
			// 3 sql insert
			sql = "insert into do_list(num,id,category,content,complete,important,deadline) values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // num구한값
			pstmt.setString(2, tb.getId());
			pstmt.setInt(3, tb.getCategory());
			pstmt.setString(4, tb.getContent());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, tb.getImportant());
			pstmt.setString(7, tb.getDeadline());
			// 4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}// insertToDoList()

	public int getListCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			// 1,2 디비연결 메서드호출
			con = getConnection();
			// 3 sql 객체 생성 count(*)
			sql = "select count(*) from do_list where complete=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			// 4 실행=> rs저장
			rs = pstmt.executeQuery();
			// 5 rs 첫행이동 데이터 있으면 count저장
			if (rs.next()) {
				count = rs.getInt(1); // rs 1번열 데이터
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return count;
	} // getListCount()

	public List getList(int startRow, int pageSize) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List ToDoList = new ArrayList();
		try {
			// 1,2 디비연결 메서드호출
			con = getConnection();
			// 3 sql 전체 정렬 order by re_ref desc, re_seq asc
			// 잘라서 가져오기 limit 시작행-1,페이지사이즈
			sql = "select * from do_list where complete=? order by deadline asc ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			// pstmt.setInt(1, startRow-1);
			// pstmt.setInt(2, pageSize);
			// 4 rs 실행 결과 저장
			rs = pstmt.executeQuery();
			// 5 while rs 첫행이동 데이터 있으면 자바빈 객체 생성 BoardBean bb
			// 자바빈 멤버변수 rs 각 열 저장 => boardList add()배열 한칸 저장
			while (rs.next()) {
				ToDoBean tb = new ToDoBean();
				tb.setId(rs.getString("id"));
				tb.setNum(rs.getInt("num"));
				tb.setCategory(rs.getInt("category"));
				tb.setContent(rs.getString("content"));
				tb.setDeadline(rs.getString("deadline"));
				tb.setImportant(rs.getInt("important"));
				tb.setComplete(rs.getInt("complete"));
				ToDoList.add(tb); // 배열한칸 bb저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return ToDoList;
	}

	// deleteToDo(num)
	public void deleteToDo(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			// 1,2 디비연결
			con = getConnection();
			// 3 goods 조건 num에 해당되는 상품 삭제
			sql = "select * from do_list where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 4 rs 실행저장
			rs = pstmt.executeQuery();
			// 5 rs첫행이동 데이터 있으면
			// //3 sql 조건 num 만족하는 글삭제 //4실행
			if (rs.next()) {
				sql = "delete from do_list where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				// 4 실행
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

	}// deleteToDo(num)

	// getToDoContent(num)
	public ToDoBean getToDoContent(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ToDoBean tb = null;
		try {
			// 1,2 디비연결
			con = getConnection();
			// 3 sql 객체 생성 num조건을 만족하는 글 가져오기
			sql = "select * from do_list where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 4 rs 실행 저장
			rs = pstmt.executeQuery();
			// 5 rs 첫행이동 데이터 있으면 bb 객체 생성
			// rs 열 => bb 멤버변수 저장
			if (rs.next()) {
				tb = new ToDoBean();
				tb.setNum(rs.getInt("num"));
				tb.setId(rs.getString("id"));
				tb.setImportant(rs.getInt("important"));
				tb.setComplete(rs.getInt("complete"));
				tb.setContent(rs.getString("content"));
				tb.setDeadline(rs.getString("deadline"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return tb;
	}

	// updateToDo(bb)
	public void updateToDo(ToDoBean tb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			// 1,2 디비연결
			con = getConnection();
			// 3 조건 num에 해당하는 pass 가져오기
			sql = "select * from do_list where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tb.getNum());
			// 4 rs 실행 저장
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 3 update
				sql = "update do_list set id=?,important=?,content=?,deadline=?,category=?  where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tb.getId());
				pstmt.setInt(2, tb.getImportant());
				pstmt.setString(3, tb.getContent());
				pstmt.setString(4, tb.getDeadline());
				pstmt.setInt(5, tb.getCategory());
				pstmt.setInt(6, tb.getNum());
				// 4 실행
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return;
	}

	public int getListCount_c() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			// 1,2 디비연결 메서드호출
			con = getConnection();
			// 3 sql 객체 생성 count(*)
			sql = "select count(*) from do_list where complete=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			// 4 실행=> rs저장
			rs = pstmt.executeQuery();
			// 5 rs 첫행이동 데이터 있으면 count저장
			if (rs.next()) {
				count = rs.getInt(1); // rs 1번열 데이터
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return count;
	} // getListCount_c()

	public List getList_c(int startRow, int pageSize) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List ToDoList = new ArrayList();
		try {
			// 1,2 디비연결 메서드호출
			con = getConnection();
			// 3 sql 전체 정렬 order by re_ref desc, re_seq asc
			// 잘라서 가져오기 limit 시작행-1,페이지사이즈
			sql = "select * from do_list where complete=? order by deadline asc";
			pstmt = con.prepareStatement(sql);
			// pstmt.setInt(1, startRow-1);
			// pstmt.setInt(2, pageSize);
			pstmt.setInt(1, 1);
			
			// 4 rs 실행 결과 저장
			rs = pstmt.executeQuery();
			// 5 while rs 첫행이동 데이터 있으면 자바빈 객체 생성 BoardBean bb
			// 자바빈 멤버변수 rs 각 열 저장 => boardList add()배열 한칸 저장
			while (rs.next()) {
				ToDoBean tb = new ToDoBean();
				tb.setId(rs.getString("id"));
				tb.setNum(rs.getInt("num"));
				tb.setCategory(rs.getInt("category"));
				tb.setContent(rs.getString("content"));
				tb.setDeadline(rs.getString("deadline"));
				tb.setImportant(rs.getInt("important"));
				tb.setComplete(rs.getInt("complete"));
				ToDoList.add(tb); // 배열한칸 bb저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return ToDoList;
	}

	public void ToDoFinish(int num, int complete) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			// 1,2 디비연결
			con = getConnection();
			// 3 goods 조건 num에 해당되는 상품 삭제
			sql = "select complete from do_list where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 4 rs 실행저장
			rs = pstmt.executeQuery();
			// 5 rs첫행이동 데이터 있으면
			// //3 sql 조건 num 만족하는 수정 //4실행
			if (rs.next()) {
				sql = "update do_list set complete=? where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, complete);
				pstmt.setInt(2, num);
				// 4 실행
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

	}

	public int getListCount(int category) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			// 1,2 디비연결 메서드호출
			con = getConnection();
			// 3 sql 객체 생성 count(*)
			sql = "select count(*) from do_list where category=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, category);
			// 4 실행=> rs저장
			rs = pstmt.executeQuery();
			// 5 rs 첫행이동 데이터 있으면 count저장
			if (rs.next()) {
				count = rs.getInt(1); // rs 1번열 데이터
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return count;
	}

	public List getList(int startRow, int pageSize, int category) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List ToDoList = new ArrayList();
		try {
			// 1,2 디비연결 메서드호출
			con = getConnection();
			// 3 sql

			sql = "select * from do_list where category=? order by deadline asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, category);
			// pstmt.setInt(1, startRow-1);
			// pstmt.setInt(2, pageSize);
			// 4 rs 실행 결과 저장
			rs = pstmt.executeQuery();
			// 5 while rs 첫행이동 데이터 있으면 자바빈 객체 생성 BoardBean bb
			// 자바빈 멤버변수 rs 각 열 저장 => boardList add()배열 한칸 저장
			while (rs.next()) {
				ToDoBean tb = new ToDoBean();
				tb.setId(rs.getString("id"));
				tb.setNum(rs.getInt("num"));
				tb.setContent(rs.getString("content"));
				tb.setDeadline(rs.getString("deadline"));
				tb.setImportant(rs.getInt("important"));
				tb.setComplete(rs.getInt("complete"));
				ToDoList.add(tb); // 배열한칸 bb저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return ToDoList;
	}
	


	
	
}
