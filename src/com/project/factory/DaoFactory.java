package com.project.factory;

import com.project.connection.ConnectionMaker;
import com.project.connection.DBConnection;
import com.project.dao.MemberDAO;
import com.project.dao.ProjectsDAO;

public class DaoFactory {

	public MemberDAO memberDAO() {
		return new MemberDAO(connectionMaker());
	}
	
	public ProjectsDAO projectsDAO() {
		return new ProjectsDAO(connectionMaker());
	}
	
	public ConnectionMaker connectionMaker() {
		return new DBConnection();
	}
}
