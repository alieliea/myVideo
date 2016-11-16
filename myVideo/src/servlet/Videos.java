package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.FileObject;
import util.FileManager;
import util.StringUtil;

@SuppressWarnings("serial")
public class Videos extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<FileObject> list = new ArrayList<FileObject>();
		response.setContentType("text/html; charset=utf-8");
	    request.setCharacterEncoding("utf-8");
	    String name = !StringUtil.isEmptyStr(request.getParameter("name")) ? "" : request.getParameter("name");
	    String path = !StringUtil.isEmptyStr(request.getParameter("path")) ? "/" : request.getParameter("path");
	    list = FileManager.search(name, path);
	    request.setAttribute("list", list);
	    request.setAttribute("name", name);
	    request.setAttribute("path", path);
	    request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}