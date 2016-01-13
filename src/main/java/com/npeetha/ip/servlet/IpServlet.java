package com.npeetha.ip.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpHandler;

/**
 * Servlet implementation class IpServlet
 */
@WebServlet("/IpServlet")
public class IpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ipAddress;
	private static String lastUpdate;
	private static String ipChangedAt;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public IpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.addHeader(Http, arg1);
		response.getWriter().append("{\"IP\":\""+ipAddress+"\", \"Last Update\": \""+lastUpdate+"\", \"Ip Change At\": \""+ipChangedAt+"\"}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		if(ipAddress==null || !ipAddress.equals(request.getRemoteAddr())){
			ipChangedAt = format.format(new Date());
		}
		ipAddress = request.getRemoteAddr();
		lastUpdate = format.format(new Date());
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ipAddress = null;
		lastUpdate = null;
	}

}
