package com.prototype.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProtoFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> map = new HashMap<String, Object>();
       
    public ProtoFrontController() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = getServletContext();
		String initPath = config.getInitParameter("configProp");
		String configPath = context.getRealPath(initPath);
		System.out.println(configPath);
		
		Properties prop = new Properties();
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(configPath);
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) { try { fis.close(); } catch (IOException e) {} }
		}
		
		Iterator<?> keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String key = (String) keyIter.next();
			String cmdClassName = prop.getProperty(key);
			try {
				Class<?> actionClass = Class.forName(cmdClassName);
				Object actionInstance = actionClass.newInstance();
				map.put(key, actionInstance);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String cmd = request.getRequestURI();
		
		if (cmd.indexOf(request.getContextPath()) == 0) {
			cmd = cmd.substring(request.getContextPath().length());
		}
		System.out.println("cmd : " + cmd);
		
		Action action = (Action) map.get(cmd);
		
		if (action == null) {
			action = new NullAction();
		}
		
		String view = null;
		
		try {
			view = action.execute(request, response);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("view : " + view);
		
		if (view != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
