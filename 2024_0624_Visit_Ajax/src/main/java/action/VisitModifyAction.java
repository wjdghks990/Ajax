package action;

import java.io.IOException;

import dao.VisitDao;
import db.vo.VisitVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitModifyAction
 */
@WebServlet("/visit/modify.do")
public class VisitModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 0. 수신 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		// 1.parameter(전달인자) 받기
		int    idx  	= Integer.parseInt(request.getParameter("idx"));
		String no   	= request.getParameter("no");		
		String name 	= request.getParameter("name");
		String content	= request.getParameter("content").replaceAll("\n", "<br>");
		String pwd 		= request.getParameter("pwd");
		
		// 2. ip정보 얻어오기
		String ip 		= request.getRemoteAddr();
		
		// 3. VisitVo 포장
		VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
		
		// 4. DB insert
		int res = VisitDao.getInstance().update(vo);
		
		// 5. 목록보기 이동
		response.sendRedirect("list.do");

	}

}
