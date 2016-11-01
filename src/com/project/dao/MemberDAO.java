package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.project.bean.MemberBean;
import com.project.connection.ConnectionMaker;

public class MemberDAO {
	private ConnectionMaker connectionMaker;
	private Connection c;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	private StringBuffer sb;

	public MemberDAO(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public boolean memberAdd(MemberBean member) {
		boolean isSuccess = false;
		
		try {
			c = connectionMaker.makeConnection();
			sb = new StringBuffer();
			sb.append("insert into member (id, pass, name, email, address, phone, mobile) ");
			sb.append(" values (?, ?, ?, ?, ?, ?, ?) ");
			sql = sb.toString();
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getMobile());
			
			if (pstmt.executeUpdate() == 1) {
				isSuccess = true;
			}
		} catch (SQLException e) {
			System.out.println("memberAdd() : " + e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			System.out.println("memberAdd() : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (Exception e) {} }
			if (c != null) { try { c.close(); } catch (Exception e) {} }
		}
		
		return isSuccess;
	}
	
	public int memberLoginCheck(String id, String pass) {
		int isCheck = -1;
		
		try {
			c = connectionMaker.makeConnection();
			sql = "select pass from member where id = ?";
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString("pass").equals(pass)) {
					isCheck = 1;
				} else {
					isCheck = 0;
				}
			}
			
		} catch (SQLException e) {
			System.out.println("memberLoginCheck() : " + e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			System.out.println("memberLoginCheck() : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (rs != null) { try { rs.close(); } catch (Exception e) {} }
			if (pstmt != null) { try { pstmt.close(); } catch (Exception e) {} }
			if (c != null) { try { c.close(); } catch (Exception e) {} }
		}
		
		return isCheck;
	}
}
