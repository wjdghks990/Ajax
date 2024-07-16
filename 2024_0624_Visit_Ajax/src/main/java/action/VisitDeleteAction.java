package action;

import java.io.IOException;

import dao.VisitDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitDeleteAction
 */
@WebServlet("/visit/delete.do")
public class VisitDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service
			(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {

		// /visit/delete.do?idx=5 - idx인자만 선택적으로 받기
		
		// 1. 삭제할 idx 수신
		int idx = Integer.parseInt(request.getParameter("idx"));
		String no = request.getParameter("no"); // 삭제할 글의 순서
		
		// 2. DB delete
		int res = VisitDao.getInstance().delete(idx); //dao에 delete() 추가
		
		// 3. 목록보기
		response.sendRedirect("list.do#p_" + no);

	}

}
