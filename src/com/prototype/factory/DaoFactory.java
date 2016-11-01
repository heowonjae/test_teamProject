package com.prototype.factory;

import com.prototype.connection.ConnectionMaker;
import com.prototype.connection.DBConnection;
import com.prototype.dao.MemberDAO;
import com.prototype.dao.ProjectsDAO;

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
