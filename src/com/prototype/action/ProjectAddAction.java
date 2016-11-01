package com.prototype.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prototype.bean.ProjectBean;
import com.prototype.dao.ProjectsDAO;
import com.prototype.factory.DaoFactory;

public class ProjectAddAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ProjectAddAction exeucte()");
		HttpSession session = request.getSession();
		ProjectBean project = new ProjectBean();
		ProjectsDAO dao = new DaoFactory().projectsDAO();
		
		String m_id = (String) session.getAttribute("id");
		String p_name = request.getParameter("name");
		String p_content = request.getParameter("content");
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp p_start = Timestamp.valueOf(request.getParameter("start") + " 00:00:00");
		Timestamp p_end = Timestamp.valueOf(request.getParameter("end") + " 23:59:59");
		
		project.setM_id(m_id);
		project.setP_name(p_name);
		project.setP_content(p_content);
		project.setP_start(p_start);
		project.setP_end(p_end);
		dao.projectAdd(project);
		response.sendRedirect("./ProjectsList.do");
		
		return null;
	}

}
