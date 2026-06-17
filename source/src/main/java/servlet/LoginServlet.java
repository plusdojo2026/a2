package servlet;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDao;
import dto.User;

@WebServlet("/a2/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	// ログインページにフォワードする
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
	dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("user_id");
		String pw = request.getParameter("password");

		// ログイン処理を行う
		UsersDao uDao = new UsersDao();
		
		User us = new User();
		us.setUserId(id);
		us.setPassword(pw);
		User user = uDao.login(us);
		if (user != null) { // ログイン成功
			// セッションスコープにIDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			// ホームサーブレットにリダイレクトする
			response.sendRedirect("/a2/webapp/homeServlet");
			
		} else { // ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			request.setAttribute("result", new Result( "IDまたはPWに間違いがあります。", null));

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/a2/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
			
			