package net.FComment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.FBoard.db.FBoardBean;

public class FComDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	private Connection getConnection() throws Exception {
		Connection con = null;
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/jspdb");
		con = ds.getConnection();
		return con;
	}

	public void insertCom(FComBean cb) {
		
		int num = 0;
		try {
			con = getConnection();
			sql = "select max(num) from com";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}
			sql = "insert into com(num,name,pass,content,subject) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, cb.getName());
			pstmt.setString(3, cb.getPass());
			pstmt.setString(4, cb.getContent());
			pstmt.setString(5, cb.getSubject());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
	} // insertCom

	public int getCommentCount() {
		
		int count = 0;
		try {
			con = getConnection();
			sql = "select count(*) from com";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1); // rs 1번열 데이터
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
		return count;
	}

	public List getCommentList(String fsub) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List commentList = new ArrayList();
		
		FComBean cb = new FComBean();
		try {
			con = getConnection();
			sql = "select name,content from com where subject = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fsub);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cb.setName(rs.getString("name"));
				cb.setContent(rs.getString("content"));
				commentList.add(cb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
		return commentList;

	}
}
