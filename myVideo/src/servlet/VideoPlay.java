package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class VideoPlay extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
	    request.setCharacterEncoding("utf-8");
	    String name = request.getParameter("name") == null ? "" : request.getParameter("name");
	    String path = request.getParameter("path") == null ? "/" : request.getParameter("path");
	    request.setAttribute("name", name);
	    request.setAttribute("path", path);
	    request.getRequestDispatcher("index2.jsp").forward(request, response);
	}
}