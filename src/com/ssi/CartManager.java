package com.ssi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartManager")
public class CartManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bcode = request.getParameter("code");
		HttpSession hs = request.getSession();
		String username = (String) hs.getAttribute("username");
		HashMap<String, ArrayList<Integer>> map;
		ArrayList<Integer> bookCodes = new ArrayList<Integer>();
		ServletContext sc1 = getServletContext();
		map = (HashMap<String, ArrayList<Integer>>) sc1.getAttribute("cart");

		if (map == null) {
			
			map = new HashMap<>();
			bookCodes.add(Integer.parseInt(bcode));
			map.put(username, bookCodes);
			sc1.setAttribute("cart", map);
	
		}
		else
		{

			if (map.containsKey(username)) {
				bookCodes = map.get(username);
				bookCodes.add(Integer.parseInt(bcode));
				map.put(username, bookCodes);
				sc1.setAttribute("cart", map);
			} else {
				bookCodes.add(Integer.parseInt(bcode));
				map.put(username, bookCodes);
				sc1.setAttribute("cart", map);
			}
		
		}

		PrintWriter out = response.getWriter();
		for (Integer x : bookCodes) 
		{
			out.print(x);
		}
	}

}
