package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public test() {
        // TODO Auto-generated constructor stub
    }
    
    static Logger log = Logger.getLogger(test.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter prt = response.getWriter();
		log.info("hello");
		//System.out.println("h");
	}

}
