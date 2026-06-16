package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");

		// ログイン処理を行う
		UsersDAO uDao = new UsersDAO();
		User us = new User( userId,  password);
		//if (uDao.insert(us)) { // ログイン成功
		//	request.setAttribute("result","登録成功しました");
			// セッションスコープにIDを格納する
			//HttpSession session = request.getSession();
			//session.setAttribute("id", new LoginUser(id));
			
			// メニューサーブレットにリダイレクトする
			//response.sendRedirect("/webapp/Servlet");
		//} else { // ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			//request.setAttribute("result", new Result("ログイン失敗！", "IDまたはPWに間違いがあります。", "/webapp/LoginServlet"));

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
			dispatcher.forward(request, response);
		}
	}

			
			