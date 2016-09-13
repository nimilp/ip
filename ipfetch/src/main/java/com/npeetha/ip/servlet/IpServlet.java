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
@WebServlet("/whatismyip")
public class IpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ipAddress;
	private static String lastUpdate;
	private static String ipChangedAt;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public IpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.getWriter().append("{\"IP\":\""+ipAddress+"\", \"LastUpdate\": \""+lastUpdate+"\", \"IpChangeAt\": \""+ipChangedAt+"\"}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(ipAddress==null || !ipAddress.equals(request.getRemoteAddr())){
			ipChangedAt = format.format(new Date());
		}
		ipAddress = request.getRemoteAddr();
		lastUpdate = format.format(new Date());
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ipAddress = null;
		lastUpdate = null;
	}

}
