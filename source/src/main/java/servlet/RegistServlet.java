package servlet;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

impor
import dao.UsersDao;

public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
	}

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
	}
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String userID = request.getParameter("userID");/* <input type="text" name="xxxx"←この部分> */
		String password = request.getParameter("password");
		String username = request.getParameter("user");
		String gender = request.getParameter("gender");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		
		// 登録処理を行う
		UsersDao uDao = new UsersDao();
		if (uDao.insert(new User(0, userID, password, username, gender, height, weight))) { // 登録成功
			request.setAttribute("result", new Result("登録成功！", "レコードを登録しました。", "/webapp/HomeServlet"));
		} else { // 登録失敗
			request.setAttribute("result", new Result("登録失敗！", "レコードを登録できませんでした。", "/webapp/MenuServlet"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}
}