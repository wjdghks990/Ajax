package action;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcAction
 */
@WebServlet("/calc.do")
public class CalcAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
			// /calc.do?su1=5&su2=8
		
		// 0. 수신 인코딩 설정
//		request.setCharacterEncoding("utf-8");
		
		// 1. parameter 받기
		int su1 = Integer.parseInt(request.getParameter("su1"));
		int su2 = Integer.parseInt(request.getParameter("su2"));
		
		int plus = su1 + su2;
		int minus = su1 - su2;
		int multiply = su1 * su2;
		int divide = su1 / su2;
		
		// 결과 전송
		response.setContentType("application/json; charset:utf-8;");
		PrintWriter out = response.getWriter();
		

		String json = String.format("{\"plus\":%d}, {\"minus\":%d}, {\"multiply\":%d} ,{\"divide\":%d}",plus, minus, multiply, divide);
		out.println(json);

	}

}