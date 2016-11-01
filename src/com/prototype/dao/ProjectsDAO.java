package com.prototype.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.prototype.bean.ProjectBean;
import com.prototype.connection.ConnectionMaker;

public class ProjectsDAO {
	private ConnectionMaker connectionMaker;
	private Connection c;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	private StringBuffer sb;

	public ProjectsDAO(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public void projectAdd(ProjectBean project) {
		int num = 0;
		
		try {
			c = connectionMaker.makeConnection();
			sql = "select count(*) cnt from projects where m_id = ?";
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, project.getM_id());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > num) {
					num = rs.getInt(1) + 1;
				} else {
					num = 1;
				}
			}
			sb = new StringBuffer();
			sb.append("INSERT INTO projects (p_num, p_name, p_content, p_start, p_end, m_id) ");
			sb.append(" values (?, ?, ?, ?, ?, ?) ");
			sql = sb.toString();
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, project.getP_name());
			pstmt.setString(3, project.getP_content());
			pstmt.setTimestamp(4, project.getP_start());
			pstmt.setTimestamp(5, project.getP_end());
			pstmt.setString(6, project.getM_id());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("projectAdd() : " + e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			System.out.println("projectAdd() : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (c != null) c.close();
			} catch (Exception e) {}
		}
	}
	
	public List<ProjectBean> getProjectList(String m_id) {
		List<ProjectBean> projectList = new ArrayList<ProjectBean>();
		
		try {
			c = connectionMaker.makeConnection();
			sql = "select * from projects where m_id = ?";
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProjectBean project = new ProjectBean();
				project.setP_num(rs.getInt("p_num"));
				project.setP_name(rs.getString("p_name"));
				project.setP_content(rs.getString("p_content"));
				project.setP_start(rs.getTimestamp("p_start"));
				project.setP_end(rs.getTimestamp("p_end"));
				project.setM_id(rs.getString("m_id"));
				project.setP_reg_date(rs.getTimestamp("p_reg_date"));
				projectList.add(project);
			}
		} catch (SQLException e) {
			System.out.println("getProjectList() : " + e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			System.out.println("getProjectList() : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (rs != null) { try { rs.close(); } catch (Exception e) {} }
			if (pstmt != null) { try { pstmt.close(); } catch (Exception e) {} }
			if (c != null) { try { c.close(); } catch (Exception e) {} }
		}
		
		return projectList;
	}
}
