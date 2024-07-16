package action;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WordAction
 */
@WebServlet("/word.do")
public class WordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		
		// /word.do?word=one
		
		// 0. 수신 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		// 1. parameter받기 // 모든 글자 대문자로 받기 - toUpperCase()
		String word = request.getParameter("word").toUpperCase();
		
		String kor_word = "";
		switch(word) 
		{
			case "ONE" : kor_word = "하나"; break;
			case "TWO" : kor_word = "둘"; break;
			case "THREE" : kor_word = "셋"; break;
			case "FOUR" : kor_word = "넷"; break;
			case "FIVE" : kor_word = "다섯"; break;
			default : kor_word = "등록되지 않은 글자입니다.";			
		}
		
		// 결과 전송
		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		out.printf("[%s](은)는 [%s](이)라는 뜻입니다.", word, kor_word);

	}

}