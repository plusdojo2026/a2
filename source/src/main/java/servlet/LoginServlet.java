package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDao;
import dto.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		System.out.println("sssssssssssssssssssssssssssssssss");
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("aaaaaaaaaaaaaaaaaaaaa");
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
			//ポイントを付与する処理
			boolean result = uDao.upDatePoint(id,5);
			if(result==true) {
				System.out.println("ポイント付与成功");
				request.setAttribute("msg","5ポイント付与されました！");
			}
			
			
			// セッションスコープにIDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			// ホームサーブレットにリダイレクトする
			response.sendRedirect("/a2/HomeServlet");
			
		} else { // ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			request.setAttribute("result", "IDまたはPWに間違いがあります。");

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
			
			