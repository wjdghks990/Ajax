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
 * Servlet implementation class VisitInsertAction
 */
@WebServlet("/visit/insert.do")
public class VisitInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(
			HttpServletRequest request, // client -> server로 들어오는 정보처리
			HttpServletResponse response) // server -> client로 들어오는 정보처리
			throws ServletException, IOException {

		// /visit/insert.do?name=홍길동&content=ㅋㅋ&pwd=1234
		
		// 0.수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		// 1.parameter(전달인자) 받기
		String name = request.getParameter("name");
		String content = request.getParameter("content").replaceAll("\n", "<br>");
		String pwd = request.getParameter("pwd");
		
		// 2. ip정보 얻어온다
		String ip = request.getRemoteAddr();
		
		// 3. VisitVo 포장
		// vo에서 생성자 추가하기
		// VisitVo vo = new  VisitVo();
		// vo.set..(); 의 과정 생략
		VisitVo vo = new VisitVo(name, content, pwd, ip);
		
		// 4. DB insert
		int res = VisitDao.getInstance().insert(vo); // dao클래스에 메서드 추가
		
		// 5. 목록보기 이동
		response.sendRedirect("list.do"); // 이미 같은 경로이기 때문에 /visit를 추가할 필요 없다.
			
	}

}
