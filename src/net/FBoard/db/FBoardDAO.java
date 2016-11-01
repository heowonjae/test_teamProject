package net.FBoard.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FBoardDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	private Connection getConnection() throws Exception {
		// String dbUrl="jdbc:mysql://localhost:3306/jspdb";
		// String dbUser="jspid";
		// String dbPass="jsppass";
		// Connection con=null;
		// 1단계 드라이버 로더
		// Class.forName("com.mysql.jdbc.Driver");
		// 2단계
		// con=DriverManager.getConnection(dbUrl,dbUser,dbPass);
		// return con;

		Connection con = null;
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/jspdb");
		con = ds.getConnection();
		return con;
	}
	
	public void insertBoard(FBoardBean fb) {
		
		int num = 0;
		try {
			// 1,2 디비연결 메서드호출
			con = getConnection();
			// num 구하기
			sql = "select max(num) from download_board";
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
			sql = "insert into download_board(num,name,pass,subject,content,readcount,ip,date,re_ref,re_lev,re_seq,file) values(?,?,?,?,?,?,?,now(),?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // num구한값
			pstmt.setString(2, fb.getName()); // name
			pstmt.setString(3, fb.getPass()); // pass
			pstmt.setString(4, fb.getSubject()); // subject
			pstmt.setString(5, fb.getContent()); // content
			pstmt.setInt(6, 0); // readcount 조회수 0
			pstmt.setString(7, fb.getIp()); // ip
			// date now()
			pstmt.setInt(8, num); // 그룹번호 re_ref == 기준 num 같게 입력
			pstmt.setInt(9, 0); // re_lev 0
			pstmt.setInt(10, 0); // re_seq 0
			pstmt.setString(11, fb.getFile()); // file
			// 4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
			if(con!=null)try{con.close();}catch(SQLException e){}
		}
	}// insertBoard()
	
	public int getBoardCount() {
		
		int count = 0;
		
		try {
			con = getConnection();
			sql = "select count(*) from download_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1); // rs 1번열 데이터
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
			if(con!=null)try{con.close();}catch(SQLException e){}
		}
		return count;
	}
	
	public List getBoardList(int startRow, int pageSize) {
		
		List boardList = new ArrayList();
		
		try {
			con = getConnection();
			sql = "select * from download_board order by re_ref desc,re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FBoardBean fb = new FBoardBean();
				fb.setNum(rs.getInt("num"));
				fb.setContent(rs.getString("content"));
				fb.setDate(rs.getDate("date"));
				fb.setFile(rs.getString("file"));
				fb.setIp(rs.getString("ip"));
				fb.setName(rs.getString("name"));
				fb.setPass(rs.getString("pass"));
				fb.setRe_lev(rs.getInt("re_lev"));
				fb.setRe_ref(rs.getInt("re_ref"));
				fb.setRe_seq(rs.getInt("re_seq"));
				fb.setReadcount(rs.getInt("readcount"));
				fb.setSubject(rs.getString("subject"));
				boardList.add(fb); // 배열한칸 fb저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
			if(con!=null)try{con.close();}catch(SQLException e){}
		}
		return boardList;
	}
	public FBoardBean getBoard(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		FBoardBean fb = null;
		try {
			con = getConnection();
			sql = "select * from download_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				fb = new FBoardBean();
				fb.setNum(rs.getInt("num"));
				fb.setContent(rs.getString("content"));
				fb.setDate(rs.getDate("date"));
				fb.setFile(rs.getString("file"));
				fb.setIp(rs.getString("ip"));
				fb.setName(rs.getString("name"));
				fb.setPass(rs.getString("pass"));
				fb.setRe_lev(rs.getInt("re_lev"));
				fb.setRe_ref(rs.getInt("re_ref"));
				fb.setRe_seq(rs.getInt("re_seq"));
				fb.setReadcount(rs.getInt("readcount"));
				fb.setSubject(rs.getString("subject"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
			if(con!=null)try{con.close();}catch(SQLException e){}
		}
		return fb;
	}
	public void updateReadCount(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = getConnection();
			sql = "update download_board set readcount=readcount+1  where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
			if(con!=null)try{con.close();}catch(SQLException e){}
		}
	}
	
	public int updateBoard(FBoardBean fb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int check = -1; // num없음
		try {
			con = getConnection();
			sql = "select pass from download_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fb.getNum());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (fb.getPass().equals(rs.getString("pass"))) {
					check = 1;// 비밀번호 맞음
					// 3 update
					sql = "update download_board set name=?,subject=?,content=?,file=?  where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, fb.getName());
					pstmt.setString(2, fb.getSubject());
					pstmt.setString(3, fb.getContent());
					pstmt.setString(4, fb.getFile());
					pstmt.setInt(5, fb.getNum());
					// 4 실행
					pstmt.executeUpdate();
				} else {
					check = 0;// 비밀번호 틀림
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
			if(con!=null)try{con.close();}catch(SQLException e){}
		}
		return check;
	}
	
	public int deleteBoard(int num, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int check = -1;
		try {
			con = getConnection();
			sql = "select pass from download_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (pass.equals(rs.getString("pass"))) {
					check = 1;
					// 3 sql delete
					sql = "delete from download_board where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					// 4 실행
					pstmt.executeUpdate();
				} else {
					check = 0;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
			if(con!=null)try{con.close();}catch(SQLException e){}
		}
		return check;
	}

}
