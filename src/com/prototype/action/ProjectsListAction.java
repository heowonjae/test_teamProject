package com.prototype.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prototype.bean.ProjectBean;
import com.prototype.dao.ProjectsDAO;
import com.prototype.factory.DaoFactory;

public class ProjectsListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ProjectsListAction execute()");
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("id");
		ProjectsDAO dao = new DaoFactory().projectsDAO();
		List<ProjectBean> list = dao.getProjectList(m_id);
		request.setAttribute("list", list);
		
		return "./projects/project_list.jsp";
	}

}
